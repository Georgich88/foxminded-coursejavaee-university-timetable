package com.foxminded.rodin.timetable.restcontrollers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.service.BuildingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class BuildingRestController {

    private static final String OPERATITON_DESCRIPTION_CREATE_BUILDING = "Create a new building";
    private static final String OPERATITON_DESCRIPTION_FIND_ALL_BUILDINGS = "Get all buildings";
    private static final String OPERATITON_DESCRIPTION_FIND_BUILDING_BY_ID = "Get a building by its id";
    private static final String OPERATITON_DESCRIPTION_UPDATE_BUILDING = "Update a building";
    private static final String OPERATITON_DESCRIPTION_DELETE_BUILDING = "Delete a building by its id";

    private static final String PARAMETER_DESCRIPTION_ID_TO_FIND = "id of building to be found";
    private static final String PARAMETER_DESCRIPTION_ID_TO_UPDATE = "id of building to be updated";
    private static final String PARAMETER_DESCRIPTION_ID_TO_DELETE = "id of building to be deleted";

    private static final String RESPONSE_DESCRIPTION_SUCCESS_FIND_BUILDING_BY_ID = "Found the building";
    private static final String RESPONSE_DESCRIPTION_BAD_REQUEST_FIND_BUILDING_BY_ID = "Invalid id supplied";
    private static final String RESPONSE_DESCRIPTION_NOT_FOUND_FIND_BUILDING_BY_ID = "Building not found";

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private BuildingService buildingService;

    @Operation(summary = OPERATITON_DESCRIPTION_FIND_ALL_BUILDINGS)
    @GetMapping(value = "/buildings")
    public List<EntityModel<Building>> findAllBuildings() {
        return buildingService.findAll().stream()
                .map(building -> toModel(building))
                .collect(Collectors.toList());
    }

    @Operation(summary = OPERATITON_DESCRIPTION_FIND_BUILDING_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_DESCRIPTION_SUCCESS_FIND_BUILDING_BY_ID, content = {
                    @Content(schema = @Schema(implementation = Building.class)) }),
            @ApiResponse(responseCode = "400", description = RESPONSE_DESCRIPTION_BAD_REQUEST_FIND_BUILDING_BY_ID, content = @Content),
            @ApiResponse(responseCode = "404", description = RESPONSE_DESCRIPTION_NOT_FOUND_FIND_BUILDING_BY_ID, content = @Content) })
    @GetMapping(value = "/buildings/{id}")
    public EntityModel<Building> findBuildingById(
            @Parameter(description = PARAMETER_DESCRIPTION_ID_TO_FIND) @PathVariable("id") long id) {
        return toModel(buildingService.findById(id));
    }

    @Operation(summary = OPERATITON_DESCRIPTION_CREATE_BUILDING)
    @PostMapping(value = "/buildings/{id}")
    public ResponseEntity<?> createBuilding(
            @Parameter(description = "id of building to be created") @PathVariable("id") long id,
            @RequestBody Building building) {
        EntityModel<Building> entityModel = toModel(buildingService.save(building));
        return toResponse(entityModel);
    }

    @Operation(summary = OPERATITON_DESCRIPTION_UPDATE_BUILDING)
    @PutMapping(value = "/buildings/{id}")
    public ResponseEntity<?> updateBuilding(
            @Parameter(description = PARAMETER_DESCRIPTION_ID_TO_UPDATE) @PathVariable("id") long id,
            @RequestBody Building building) {
        EntityModel<Building> entityModel = toModel(buildingService.save(building));
        return toResponse(entityModel);
    }

    @Operation(summary = OPERATITON_DESCRIPTION_DELETE_BUILDING)
    @DeleteMapping(value = "/buildings/{id}")
    public ResponseEntity<?> deleteBuilding(
            @Parameter(description = PARAMETER_DESCRIPTION_ID_TO_DELETE) @PathVariable("id") Long id) {
        buildingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Building> toModel(Building building) {
        return EntityModel.of(building,
                linkTo(methodOn(BuildingRestController.class, restVersion).findBuildingById(building.getId())).withSelfRel(),
                linkTo(methodOn(BuildingRestController.class, restVersion).findAllBuildings()).withRel("buildings"));
    }

    public ResponseEntity<EntityModel<Building>> toResponse(EntityModel<Building> entityModel) {
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

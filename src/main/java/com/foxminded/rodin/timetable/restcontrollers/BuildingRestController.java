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

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class BuildingRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/buildings")
    public List<EntityModel<Building>> findAllBuildings() {
        return buildingService.findAll().stream()
                .map(building -> toModel(building))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/buildings/{id}")
    public EntityModel<Building> findBuildingById(@PathVariable("id") Long id) {
        return toModel(buildingService.findById(id));
    }

    @PostMapping(value = "/buildings/{id}")
    public ResponseEntity<?> createBuilding(@RequestBody Building building) {
        EntityModel<Building> entityModel = toModel(buildingService.save(building));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/buildings/{id}")
    public ResponseEntity<?> updateBuilding(@RequestBody Building building) {
        EntityModel<Building> entityModel = toModel(buildingService.save(building));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/buildings/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable("id") Long id) {
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

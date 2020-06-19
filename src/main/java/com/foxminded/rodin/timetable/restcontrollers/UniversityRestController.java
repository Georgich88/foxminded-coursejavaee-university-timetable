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

import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.service.UniversityService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class UniversityRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private UniversityService universityService;

    @GetMapping(value = "/universities")
    public List<EntityModel<University>> findAllUniversities() {
        return universityService.findAll().stream()
                .map(university -> toModel(university))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/universities/{id}")
    public EntityModel<University> findUniversityById(@PathVariable("id") Long id) {
        return toModel(universityService.findById(id));
    }

    @PostMapping(value = "/universities/{id}")
    public ResponseEntity<?> createUniversity(@RequestBody University university) {
        EntityModel<University> entityModel = toModel(universityService.save(university));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/universities/{id}")
    public ResponseEntity<?> updateUniversity(@RequestBody University university) {
        EntityModel<University> entityModel = toModel(universityService.save(university));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/universities/{id}")
    public ResponseEntity<?> deleteUniversity(@PathVariable("id") Long id) {
        universityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<University> toModel(University university) {
        return EntityModel.of(university,
                linkTo(methodOn(UniversityRestController.class, restVersion).findUniversityById(university.getId()))
                        .withSelfRel(),
                linkTo(methodOn(UniversityRestController.class, restVersion).findAllUniversities())
                        .withRel("universities"));
    }

    public ResponseEntity<EntityModel<University>> toResponse(EntityModel<University> entityModel) {
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

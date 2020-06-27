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

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.service.FacultyService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class FacultyRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/faculties")
    public List<EntityModel<Faculty>> findAllFaculties() {
        return facultyService.findAll().stream()
                .map(faculty -> toModel(faculty))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/faculties/{id}")
    public EntityModel<Faculty> findFacultyById(@PathVariable("id") Long id) {
        return toModel(facultyService.findById(id));
    }

    @PostMapping(value = "/faculties/{id}")
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        EntityModel<Faculty> entityModel = toModel(facultyService.save(faculty));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/faculties/{id}")
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty) {
        EntityModel<Faculty> entityModel = toModel(facultyService.save(faculty));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/faculties/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable("id") Long id) {
        facultyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Faculty> toModel(Faculty faculty) {
        return EntityModel.of(faculty,
                linkTo(methodOn(FacultyRestController.class, restVersion).findFacultyById(faculty.getId())).withSelfRel(),
                linkTo(methodOn(FacultyRestController.class, restVersion).findAllFaculties()).withRel("faculties"));
    }

    public ResponseEntity<EntityModel<Faculty>> toResponse(EntityModel<Faculty> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

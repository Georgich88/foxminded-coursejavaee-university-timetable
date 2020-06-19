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

import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.service.SubjectService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class SubjectRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/subjects")
    public List<EntityModel<Subject>> findAllSubjects() {
        return subjectService.findAll().stream()
                .map(subject -> toModel(subject))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/subjects/{id}")
    public EntityModel<Subject> findSubjectById(@PathVariable("id") Long id) {
        return toModel(subjectService.findById(id));
    }

    @PostMapping(value = "/subjects/{id}")
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        EntityModel<Subject> entityModel = toModel(subjectService.save(subject));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/subjects/{id}")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject) {
        EntityModel<Subject> entityModel = toModel(subjectService.save(subject));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/subjects/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Subject> toModel(Subject subject) {
        return EntityModel.of(subject,
                linkTo(methodOn(SubjectRestController.class, restVersion).findSubjectById(subject.getId())).withSelfRel(),
                linkTo(methodOn(SubjectRestController.class, restVersion).findAllSubjects()).withRel("subjects"));
    }

    public ResponseEntity<EntityModel<Subject>> toResponse(EntityModel<Subject> entityModel) {
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

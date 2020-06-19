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

import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.service.TeacherService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class TeacherRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/teachers")
    public List<EntityModel<Teacher>> findAllTeachers() {
        return teacherService.findAll().stream()
                .map(teacher -> toModel(teacher))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/teachers/{id}")
    public EntityModel<Teacher> findTeacherById(@PathVariable("id") Long id) {
        return toModel(teacherService.findById(id));
    }

    @PostMapping(value = "/teachers/{id}")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        EntityModel<Teacher> entityModel = toModel(teacherService.save(teacher));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/teachers/{id}")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher) {
        EntityModel<Teacher> entityModel = toModel(teacherService.save(teacher));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Teacher> toModel(Teacher teacher) {
        return EntityModel.of(teacher,
                linkTo(methodOn(TeacherRestController.class, restVersion).findTeacherById(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherRestController.class, restVersion).findAllTeachers()).withRel("teachers"));
    }

    public ResponseEntity<EntityModel<Teacher>> toResponse(EntityModel<Teacher> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

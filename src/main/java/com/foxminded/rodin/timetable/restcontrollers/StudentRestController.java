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

import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.service.StudentService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class StudentRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<EntityModel<Student>> findAllStudents() {
        return studentService.findAll().stream()
                .map(student -> toModel(student))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/students/{id}")
    public EntityModel<Student> findStudentById(@PathVariable("id") Long id) {
        return toModel(studentService.findById(id));
    }

    @PostMapping(value = "/students/{id}")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        EntityModel<Student> entityModel = toModel(studentService.save(student));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        EntityModel<Student> entityModel = toModel(studentService.save(student));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Student> toModel(Student student) {
        return EntityModel.of(student,
                linkTo(methodOn(StudentRestController.class, restVersion).findStudentById(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentRestController.class, restVersion).findAllStudents()).withRel("students"));
    }

    public ResponseEntity<EntityModel<Student>> toResponse(EntityModel<Student> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

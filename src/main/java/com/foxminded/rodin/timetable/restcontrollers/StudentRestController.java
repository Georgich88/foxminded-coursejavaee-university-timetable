package com.foxminded.rodin.timetable.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/${timetable.rest.version}")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping(value = "/students/{id}")
    public Student findStudentById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @PostMapping(value = "/students/{id}")
    public void createStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping(value = "/students/{id}")
    public void updateStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

}

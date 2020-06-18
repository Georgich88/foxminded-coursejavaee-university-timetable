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

import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.service.TeacherService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/teachers")
    public List<Teacher> findAllTeachers() {
        return teacherService.findAll();
    }

    @GetMapping(value = "/teachers/{id}")
    public Teacher findTeacherById(@PathVariable("id") Long id) {
        return teacherService.findById(id);
    }

    @PostMapping(value = "/teachers/{id}")
    public void createTeacher(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
    }

    @PutMapping(value = "/teachers/{id}")
    public void updateTeacher(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
    }

    @DeleteMapping(value = "/teachers/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteById(id);
    }

}

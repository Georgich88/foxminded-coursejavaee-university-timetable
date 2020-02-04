package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.service.StudentService;

@Controller
public class StudentController {

    private static final String STUDENTS_LIST_FORM_RESOURSE_NAME = "students";

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model, Principal principal) {

        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);
        model.addAttribute("activeAll", true);

        return STUDENTS_LIST_FORM_RESOURSE_NAME;
    }

}

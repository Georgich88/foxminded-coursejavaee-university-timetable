package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.service.TeacherService;

@Controller
public class TeacherController {

    private static final String TEACHER_FORM_RESOURSE_NAME = "teacher";
    private static final String TEACHERS_LIST_FORM_RESOURSE_NAME = "teachers";

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public String getAllTeachers(Model model, Principal principal) {

        List<Teacher> teachers = teacherService.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("activeAll", true);

        return TEACHERS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/teachers/new")
    public String showNewTeacherForm(Model model, Principal principal) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return TEACHER_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/teachers/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return TEACHER_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/teachers/{id}/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/" + TEACHERS_LIST_FORM_RESOURSE_NAME;
    }

}

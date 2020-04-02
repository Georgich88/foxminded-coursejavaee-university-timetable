package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.service.SubjectService;

@Controller
public class SubjectController {

    private static final String SUBJECTS_LIST_FORM_RESOURSE_NAME = "subjects";

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public String getAllSubjecs(Model model, Principal principal) {

        List<Subject> subjects = subjectService.findAll();

        model.addAttribute("subjects", subjects);
        model.addAttribute("activeAll", true);

        return SUBJECTS_LIST_FORM_RESOURSE_NAME;
    }

}

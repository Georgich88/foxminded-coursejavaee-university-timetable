package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.service.FacultyService;

@Controller
public class FacultyController {

    private static final String FACULTIES_LIST_FORM_RESOURSE_NAME = "faculties";

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculties")
    public String getAllFaculties(Model model, Principal principal) {

        List<Faculty> faculties = facultyService.findAll();

        model.addAttribute("faculties", faculties);
        model.addAttribute("activeAll", true);

        return FACULTIES_LIST_FORM_RESOURSE_NAME;
    }

}

package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.service.UniversityService;

@Controller
public class UniversityController {

    private static final String UNIVERSITIES_LIST_FORM_RESOURSE_NAME = "universities";

    @Autowired
    private UniversityService unversityService;

    @RequestMapping("/universities")
    public String getAllUniversities(Model model, Principal principal) {

        List<University> universities = unversityService.findAll();

        model.addAttribute("universities", universities);
        model.addAttribute("activeAll", true);

        return UNIVERSITIES_LIST_FORM_RESOURSE_NAME;
    }

}
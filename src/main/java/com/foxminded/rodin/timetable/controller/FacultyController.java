package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.service.FacultyService;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping("/faculties")
    public String facultyList(Model model, Principal principal) {

        List<Faculty> faculties = facultyService.findAll();

        model.addAttribute("faculties", faculties);
        model.addAttribute("activeAll", true);

        return "faculties";
    }

}

package com.foxminded.rodin.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.service.BuildingService;

@Controller
public class BuildingController {

    private static final String BUILDINGS_LIST_FORM_RESOURSE_NAME = "buildings";

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/buildings")
    public String getAllBuildings(Model model) {

        List<Building> buildings = buildingService.findAll();

        model.addAttribute("buildings", buildings);
        model.addAttribute("activeAll", true);

        return BUILDINGS_LIST_FORM_RESOURSE_NAME;
    }

}

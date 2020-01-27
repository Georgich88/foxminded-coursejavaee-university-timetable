package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.service.BuildingService;

@Controller
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping("/buildings")
    public String BuildingList(Model model, Principal principal) {

        List<Building> buildings = buildingService.findAll();

        model.addAttribute("buildings", buildings);
        model.addAttribute("activeAll", true);

        return "buildings";
    }

}

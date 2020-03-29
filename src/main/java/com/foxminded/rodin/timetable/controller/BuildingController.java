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
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.service.BuildingService;
import com.foxminded.rodin.timetable.service.RoomService;

@Controller
public class BuildingController {

    private static final String BUILDING_FORM_RESOURSE_NAME = "building";
    private static final String BUILDINGS_LIST_FORM_RESOURSE_NAME = "buildings";

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/buildings")
    public String allBuildings(Model model) {

        List<Building> buildings = buildingService.findAll();

        model.addAttribute("buildings", buildings);
        model.addAttribute("activeAll", true);

        return BUILDINGS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/buildings/new")
    public String addNewBuilding(Model model, Principal principal) {
        Building building = new Building();
        model.addAttribute("building", building);
        return BUILDING_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/buildings/{id}/save")
    public String saveBuilding(@ModelAttribute("building") Building building) {
        roomService.saveAll(building.getRooms());
        buildingService.save(building);
        return "redirect:/" + BUILDINGS_LIST_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/buildings/{id}/delete")
    public String deleteBuilding(@PathVariable("id") Long id) {
        buildingService.deleteById(id);
        return "redirect:/" + BUILDINGS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/buildings/{id}")
    public String editBuilding(@PathVariable("id") Long id, Model model) {
        Building building = buildingService.findById(id);
        model.addAttribute("building", building);
        return BUILDING_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/buildings/{id}/add-room")
    public String addRoom(Building building) {
        building.addNewRoom("", 0);
        return BUILDING_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/buildings/{id}/remove-room", params = { "rowId" })
    public String removeRoom(@RequestParam("rowId") int rowId, Building building) {
        building.getRooms().remove(rowId);
        return BUILDING_FORM_RESOURSE_NAME;
    }

}

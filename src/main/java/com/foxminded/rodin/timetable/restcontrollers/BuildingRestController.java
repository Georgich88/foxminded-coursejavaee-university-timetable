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

import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.service.BuildingService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class BuildingRestController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/buildings")
    public List<Building> findAllBuildings() {
        return buildingService.findAll();
    }

    @GetMapping(value = "/buildings/{id}")
    public Building findBuildingById(@PathVariable("id") Long id) {
        return buildingService.findById(id);
    }

    @PostMapping(value = "/buildings/{id}")
    public void createBuilding(@RequestBody Building building) {
        buildingService.save(building);
    }

    @PutMapping(value = "/buildings/{id}")
    public void updateBuilding(@RequestBody Building building) {
        buildingService.save(building);
    }

    @DeleteMapping(value = "/buildings/{id}")
    public void deleteBuilding(@PathVariable("id") Long id) {
        buildingService.deleteById(id);
    }

}

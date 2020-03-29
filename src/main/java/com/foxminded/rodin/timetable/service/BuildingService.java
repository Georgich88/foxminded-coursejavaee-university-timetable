package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.repo.BuildingRepository;
import com.foxminded.rodin.timetable.repo.RoomRepository;

@Service
public class BuildingService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a building by id=%d";

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository RoomRepository;

    public List<Building> findAll() {
        List<Building> buildings = (List<Building>) buildingRepository.findAll();
        return buildings;
    }

    public Building save(Building building) {
        return buildingRepository.save(building);
    }

    public Building findById(long id) {
        return buildingRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    @Transactional
    public void deleteById(long id) {

        Building building = buildingRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        building.getRooms().clear();
        buildingRepository.delete(building);
        RoomRepository.deleteAll(building.getRooms());

    }

}

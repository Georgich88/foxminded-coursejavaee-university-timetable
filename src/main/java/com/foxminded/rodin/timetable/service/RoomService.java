package com.foxminded.rodin.timetable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.repo.RoomRepository;

@Service
public class RoomService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a room by id=%d";

    @Autowired
    private RoomRepository sectionRepository;

    public Iterable<Room> findAll() {
        return sectionRepository.findAll();
    }

    public Iterable<Room> saveAll(Iterable<Room> sections) {
        return sectionRepository.saveAll(sections);
    }

    public Room findById(long id) {
        return sectionRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    public void deleteAll(@NonNull Iterable<Room> sections) {
        sectionRepository.deleteAll(sections);
    }

}

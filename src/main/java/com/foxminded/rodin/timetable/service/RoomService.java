package com.foxminded.rodin.timetable.service;

import java.util.List;

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
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> saveAll(Iterable<Room> rooms) {
        return (List<Room>) roomRepository.saveAll(rooms);
    }

    public Room findById(long id) {
        return roomRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    public void deleteAll(@NonNull Iterable<Room> rooms) {
        roomRepository.deleteAll(rooms);
    }

    public void deleteById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        roomRepository.deleteBuildingRoomsByRoomId(id);
        roomRepository.delete(room);

    }

}

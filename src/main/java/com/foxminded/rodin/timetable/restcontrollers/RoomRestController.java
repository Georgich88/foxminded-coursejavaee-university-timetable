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

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.service.RoomService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class RoomRestController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<Room> findAllRooms() {
        return (List<Room>) roomService.findAll();
    }

    @GetMapping(value = "/rooms/{id}")
    public Room findRoomById(@PathVariable("id") Long id) {
        return roomService.findById(id);
    }

    @PostMapping(value = "/rooms/{id}")
    public void createRoom(@RequestBody Room room) {
        roomService.save(room);
    }

    @PutMapping(value = "/rooms/{id}")
    public void updateRoom(@RequestBody Room room) {
        roomService.save(room);
    }

    @DeleteMapping(value = "/rooms/{id}")
    public void deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteById(id);
    }

}

package com.foxminded.rodin.timetable.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.service.RoomService;

@Controller
public class RoomController {

    private static final String ROOMS_LIST_FORM_RESOURSE_NAME = "rooms";

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String getAllRooms(Model model, Principal principal) {

        Iterable<Room> rooms = roomService.findAll();

        model.addAttribute("rooms", rooms);
        model.addAttribute("activeAll", true);

        return ROOMS_LIST_FORM_RESOURSE_NAME;
    }

}

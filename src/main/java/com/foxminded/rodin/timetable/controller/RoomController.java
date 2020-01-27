package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.service.RoomService;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/rooms")
    public String RoomList(Model model, Principal principal) {

        List<Room> rooms = roomService.findAll();

        model.addAttribute("rooms", rooms);
        model.addAttribute("activeAll", true);

        return "rooms";
    }

}

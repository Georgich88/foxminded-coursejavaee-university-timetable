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

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.service.RoomService;
import com.foxminded.rodin.timetable.service.ScheduleService;
import com.foxminded.rodin.timetable.service.SlotService;

@Controller
public class ScheduleController {

    private static final String SCHEDULE_FORM_RESOURSE_NAME = "schedule";
    private static final String SCHEDULES_LIST_FORM_RESOURSE_NAME = "schedules";

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SlotService slotService;

    @GetMapping(value = "/schedules")
    public String allSchedules(Model model) {

        List<Schedule> schedules = scheduleService.findAll();

        model.addAttribute("schedules", schedules);
        model.addAttribute("activeAll", true);

        return SCHEDULES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/schedules/new")
    public String addNewSchedule(Model model, Principal principal) {
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);
        return SCHEDULE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/schedules/{id}/save")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        slotService.saveAll(schedule.getSlots());
        scheduleService.save(schedule);
        return "redirect:/" + SCHEDULES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/schedules/{id}")
    public String editSchedule(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.findById(id);
        model.addAttribute("schedule", schedule);
        return SCHEDULE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/schedules/{id}/add-slot")
    public String addSlot(Schedule schedule) {
        schedule.getSlots().add(new Slot());
        return SCHEDULE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/schedules/{id}/remove-slot", params = { "rowId" })
    public String removeSlot(@RequestParam("rowId") String rowIdParam, Schedule schedule) {
        Integer rowId = Integer.valueOf(rowIdParam);
        schedule.getSlots().remove(rowId.intValue());
        return SCHEDULE_FORM_RESOURSE_NAME;
    }

    @ModelAttribute("availableRooms")
    public List<Room> populateVarieties(Schedule schedule, Model model) {
        return roomService.findAll();
    }
}

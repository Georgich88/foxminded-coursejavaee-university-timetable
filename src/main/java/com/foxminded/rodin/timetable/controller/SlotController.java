package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.service.GroupService;
import com.foxminded.rodin.timetable.service.RoomService;
import com.foxminded.rodin.timetable.service.SlotService;
import com.foxminded.rodin.timetable.service.TeacherService;

@Controller
public class SlotController {

    private static final String SLOT_FORM_RESOURSE_NAME = "slot";
    private static final String SLOTS_LIST_FORM_RESOURSE_NAME = "slots";

    @Autowired
    private SlotService slotService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/slots")
    public String getAllSlots(Model model, Principal principal) {

        List<Slot> slots = slotService.findAll();

        model.addAttribute("slots", slots);
        model.addAttribute("activeAll", true);

        return SLOTS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/slots/new")
    public String createSlot(Model model, Principal principal) {
        Slot slot = new Slot();
        model.addAttribute("slot", slot);
        return SLOT_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/slots/{id}/save")
    public String addBookPost(@ModelAttribute("slot") Slot slot, BindingResult bindingResult, Model model,
            HttpServletRequest request) {

        slotService.save(slot);
        return "redirect:/" + SLOTS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/slots/{id}")
    public String editSlot(@PathVariable("id") Long id, Model model, Principal principal) {

        Slot slot = slotService.findById(id);
        model.addAttribute("slot", slot);
        return SLOT_FORM_RESOURSE_NAME;
    }

    @ModelAttribute("availableRooms")
    public Iterable<Room> populateVarietiesRooms(Slot slot, Model model) {
        if (slot != null) {
            return slotService.findAvailableRooms(slot.getId(), slot.getStartTime(), slot.getEndTime());
        } else {
            return roomService.findAll();
        }
    }

    @ModelAttribute("availableGroups")
    public List<Group> populateVarietiesGrops(Slot slot, Model model) {
        if (slot != null) {
            return slotService.findAvailableGroups(slot.getId(), slot.getStartTime(), slot.getEndTime());
        } else {
            return groupService.findAll();
        }
    }

    @ModelAttribute("availableInstructors")
    public List<Teacher> populateVarietiesInstructors(Slot slot, Model model) {
        if (slot != null) {
            return slotService.findAvailableTeachers(slot.getId(), slot.getStartTime(), slot.getEndTime());
        } else {
            return teacherService.findAll();
        }
    }

}

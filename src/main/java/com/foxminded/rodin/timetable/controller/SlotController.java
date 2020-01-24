package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@Autowired
	private SlotService slotService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private RoomService roomService;

	@RequestMapping("/slots")
	public String SlotList(Model model, Principal principal) {

		List<Slot> slots = slotService.findAll();

		model.addAttribute("slots", slots);
		model.addAttribute("activeAll", true);

		return "slots";
	}

	@RequestMapping(value = "/slot")
	public String addNewSlot(Model model, Principal principal) {
		var slot = new Slot();
		model.addAttribute("slot", slot);
		return "slot";
	}

	@RequestMapping(value = "/slot", params = { "save" }, method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("slot") Slot slot, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		slotService.save(slot);
		return "redirect:/slots";
	}

	@RequestMapping(value = "/slot", params = { "id" })
	public String editSlot(@PathParam("id") Long id, Model model, Principal principal) {

		Slot slot = slotService.findById(id);
		model.addAttribute("slot", slot);
		return "slot";
	}

	@ModelAttribute("availableRooms")
	public List<Room> populateVarietiesRooms(Slot slot, Model model) {
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

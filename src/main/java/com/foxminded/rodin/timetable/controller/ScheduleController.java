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
import com.foxminded.rodin.timetable.model.schedules.Schedule;
import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.service.RoomService;
import com.foxminded.rodin.timetable.service.ScheduleService;
import com.foxminded.rodin.timetable.service.SlotService;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private SlotService slotService;

	@RequestMapping("/schedules")
	public String ScheduleList(Model model, Principal principal) {

		List<Schedule> schedules = scheduleService.findAll();

		model.addAttribute("schedules", schedules);
		model.addAttribute("activeAll", true);

		return "schedules";
	}

	@RequestMapping(value = "/schedule")
	public String addNewSchedule(Model model, Principal principal) {
		var schedule = new Schedule();
		model.addAttribute("schedule", schedule);
		return "schedule";
	}

	@RequestMapping(value = "/schedule", params = { "save" }, method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("schedule") Schedule schedule, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		slotService.saveAll(schedule.getSlots());
		scheduleService.save(schedule);
		return "redirect:/schedules";
	}

	@RequestMapping(value = "/schedule", params = { "id" })
	public String editSchedule(@PathParam("id") Long id, Model model, Principal principal) {

		Schedule schedule = scheduleService.findById(id);
		model.addAttribute("schedule", schedule);
		return "schedule";
	}

	@RequestMapping(value = "/schedule", params = { "addSlot" }, method = RequestMethod.POST)
	public String addSlot(Schedule schedule, Model model, Principal principal) {
		schedule.getSlots().add(new Slot());
		return "schedule";
	}

	@RequestMapping(value = "/schedule", params = { "removeSlot" }, method = RequestMethod.POST)
	public String removeSlot(Schedule schedule, BindingResult bindingResult, HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeSlot"));
		schedule.getSlots().remove(rowId.intValue());
		return "schedule";
	}

	@ModelAttribute("availableRooms")
	public List<Room> populateVarieties(Schedule schedule, Model model) {
		return roomService.findAll();
	}
}

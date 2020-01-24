package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.service.GroupService;

@Controller
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping("/groups")
	public String GroupList(Model model, Principal principal) {

		List<Group> groups = groupService.findAll();

		model.addAttribute("groups", groups);
		model.addAttribute("activeAll", true);

		return "groups";
	}

}

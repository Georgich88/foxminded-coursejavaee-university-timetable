package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@RequestMapping("/teachers")
	public String TeacherList(Model model, Principal principal) {

		List<Teacher> teachers = teacherService.findAll();

		model.addAttribute("teachers", teachers);
		model.addAttribute("activeAll", true);

		return "teachers";
	}

}

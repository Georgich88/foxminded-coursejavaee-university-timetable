package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/courses")
	public String CourseList(Model model, Principal principal) {

		List<Course> courses = courseService.findAll();

		model.addAttribute("courses", courses);
		model.addAttribute("activeAll", true);

		return "courses";
	}

}

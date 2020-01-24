package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/students")
	public String StudentList(Model model, Principal principal) {

		List<Student> students = studentService.findAll();

		model.addAttribute("students", students);
		model.addAttribute("activeAll", true);

		return "students";
	}

}

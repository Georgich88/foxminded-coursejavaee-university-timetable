package com.foxminded.rodin.timetable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.service.CourseService;

@Controller
public class CourseController {

    private static final String COURSES_LIST_FORM_RESOURSE_NAME = "courses";

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(Model model) {

        List<Course> courses = courseService.findAll();

        model.addAttribute("courses", courses);
        model.addAttribute("activeAll", true);

        return COURSES_LIST_FORM_RESOURSE_NAME;
    }

}

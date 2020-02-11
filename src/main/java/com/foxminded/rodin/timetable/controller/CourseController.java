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

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.service.CourseSectionService;
import com.foxminded.rodin.timetable.service.CourseService;

@Controller
public class CourseController {

    private static final String COURSE_FORM_RESOURSE_NAME = "course";
    private static final String COURSES_LIST_FORM_RESOURSE_NAME = "courses";

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSectionService sectionService;

    @GetMapping(value = "/courses")
    public String allCourses(Model model) {

        List<Course> courses = courseService.findAll();

        model.addAttribute("courses", courses);
        model.addAttribute("activeAll", true);

        return COURSES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/courses/new")
    public String addNewCourse(Model model, Principal principal) {
        Course course = new Course();
        model.addAttribute("course", course);
        return COURSE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/courses/{id}/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        sectionService.saveAll(course.getSections());
        courseService.save(course);
        return "redirect:/" + COURSES_LIST_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/courses/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return "redirect:/" + COURSES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/courses/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        return COURSE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/courses/{id}/add-course-section")
    public String addCourseSection(Course course) {
        course.addNewSection("", "", "", 0);
        return COURSE_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/courses/{id}/remove-course-section", params = { "rowId" })
    public String removeCourseSection(@RequestParam("rowId") String rowIdParam, Course course) {
        Integer rowId = Integer.valueOf(rowIdParam);
        course.getSections().remove(rowId.intValue());
        return COURSE_FORM_RESOURSE_NAME;
    }

}

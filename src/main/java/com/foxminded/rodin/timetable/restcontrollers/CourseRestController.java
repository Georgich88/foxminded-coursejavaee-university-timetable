package com.foxminded.rodin.timetable.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.service.CourseService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public List<Course> findAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(value = "/courses/{id}")
    public Course findCourseById(@PathVariable("id") Long id) {
        return courseService.findById(id);
    }

    @PostMapping(value = "/courses/{id}")
    public void createCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping(value = "/courses/{id}")
    public void updateCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @DeleteMapping(value = "/courses/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }

}

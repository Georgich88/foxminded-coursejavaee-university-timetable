package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.repo.CourseRepository;
import com.foxminded.rodin.timetable.repo.CourseSectionRepository;
import com.foxminded.rodin.timetable.repo.SubjectRepository;

@Service
public class CourseService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a course by id=%d";

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSectionRepository courseSectionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Course> findAll() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    @Transactional
    public void deleteById(long id) {

        Course course = courseRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        subjectRepository.deleteSubjectCoursesByCourseId(id);
        course.getSections().clear();
        courseRepository.delete(course);
        courseSectionRepository.deleteAll(course.getSections());

    }

}

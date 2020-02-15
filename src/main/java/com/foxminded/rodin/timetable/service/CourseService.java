package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.repo.CourseRepository;
import com.foxminded.rodin.timetable.repo.CourseSectionRepository;
import com.foxminded.rodin.timetable.repo.SubjectRepository;

@Service
public class CourseService {

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

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    @Transactional
    public void deleteById(@NonNull Long id) {

        Course course = courseRepository.findById(id).orElseThrow(ElementNotFoundException::new);
        subjectRepository.deleteSubjectCoursesByCourseId(id);
        course.getSections().clear();
        courseRepository.delete(course);
        courseSectionRepository.deleteAll(course.getSections());

    }

}

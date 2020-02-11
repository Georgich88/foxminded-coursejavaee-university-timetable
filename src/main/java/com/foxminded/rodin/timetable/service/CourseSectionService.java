package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.curriculums.CourseSection;
import com.foxminded.rodin.timetable.repo.CourseSectionRepository;

@Service
public class CourseSectionService {

    @Autowired
    private CourseSectionRepository sectionRepository;

    public List<CourseSection> findAll() {
        List<CourseSection> courseSections = (List<CourseSection>) sectionRepository.findAll();
        return courseSections;
    }

    public CourseSection save(CourseSection courseSection) {
        return sectionRepository.save(courseSection);
    }

    public Iterable<CourseSection> saveAll(Iterable<CourseSection> courseSections) {
        return sectionRepository.saveAll(courseSections);
    }

    public List<CourseSection> saveAll(List<CourseSection> courseSections) {
        return (List<CourseSection>) sectionRepository.saveAll(courseSections);
    }

    public CourseSection findById(@NonNull Long id) {
        return sectionRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void deleteAll(@NonNull Iterable<CourseSection> sections) {
        sectionRepository.deleteAll(sections);
    }

}

package com.foxminded.rodin.timetable.service;

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

    public Iterable<CourseSection> findAll() {
        return sectionRepository.findAll();
    }

    public Iterable<CourseSection> saveAll(Iterable<CourseSection> sections) {
        return sectionRepository.saveAll(sections);
    }

    public CourseSection findById(@NonNull Long id) {
        return sectionRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void deleteAll(@NonNull Iterable<CourseSection> sections) {
        sectionRepository.deleteAll(sections);
    }

}

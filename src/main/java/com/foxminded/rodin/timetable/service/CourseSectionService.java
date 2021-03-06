package com.foxminded.rodin.timetable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.curriculums.CourseSection;
import com.foxminded.rodin.timetable.repo.CourseSectionRepository;

@Service
public class CourseSectionService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a course section by id=%d";

    @Autowired
    private CourseSectionRepository sectionRepository;

    public Iterable<CourseSection> findAll() {
        return sectionRepository.findAll();
    }

    public Iterable<CourseSection> saveAll(Iterable<CourseSection> sections) {
        return sectionRepository.saveAll(sections);
    }

    public CourseSection findById(long id) {
        return sectionRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    public void deleteAll(@NonNull Iterable<CourseSection> sections) {
        sectionRepository.deleteAll(sections);
    }

}

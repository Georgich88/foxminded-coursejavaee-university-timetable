package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.repo.FacultyRepository;
import com.foxminded.rodin.timetable.repo.UniversityRepository;

@Service
public class UniversityService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a university by id=%d";

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<University> findAll() {
        List<University> universities = (List<University>) universityRepository.findAll();
        return universities;
    }

    public University save(University university) {
        return universityRepository.save(university);
    }

    public University findById(long id) {
        return universityRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    @Transactional
    public void deleteById(long id) {

        University university = universityRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        university.getFaculties().clear();
        universityRepository.delete(university);
        facultyRepository.deleteAll(university.getFaculties());

    }

}

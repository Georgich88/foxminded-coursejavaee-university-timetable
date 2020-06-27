package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.repo.FacultyRepository;
import com.foxminded.rodin.timetable.repo.GroupRepository;

@Service
public class FacultyService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a faculty by id=%d";

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Faculty> findAll() {
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();
        return faculties;
    }

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Iterable<Faculty> saveAll(Iterable<Faculty> sections) {
        return facultyRepository.saveAll(sections);
    }

    public Faculty findById(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    @Transactional
    public void deleteById(long id) {

        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        groupRepository.deleteAll(faculty.getGroups());
        faculty.getGroups().clear();
        facultyRepository.delete(faculty);

    }

}

package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.repo.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> findAll() {
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();
        return faculties;
    }

}

package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.repo.UniversityRepository;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<University> findAll() {
        List<University> universities = (List<University>) universityRepository.findAll();
        return universities;
    }

}

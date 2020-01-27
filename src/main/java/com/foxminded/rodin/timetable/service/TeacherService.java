package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.repo.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
        return teachers;
    }

}

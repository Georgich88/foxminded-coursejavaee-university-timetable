package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.repo.StudentRepository;

@Service
public class StudentService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a student by id=%d";

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(@NonNull Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

}

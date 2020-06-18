package com.foxminded.rodin.timetable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.repo.SubjectRepository;

@Service
public class SubjectService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a subject by id=%d";

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        List<Subject> subjects = (List<Subject>) subjectRepository.findAll();
        return subjects;
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject findById(@NonNull Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    @Transactional
    public void deleteById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
        subjectRepository.deleteSubjectCoursesByCourseId(id);
        subjectRepository.delete(subject);

    }

}

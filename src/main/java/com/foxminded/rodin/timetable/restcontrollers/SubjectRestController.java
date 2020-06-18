package com.foxminded.rodin.timetable.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.service.SubjectService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class SubjectRestController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/subjects")
    public List<Subject> findAllSubjects() {
        return subjectService.findAll();
    }

    @GetMapping(value = "/subjects/{id}")
    public Subject findSubjectById(@PathVariable("id") Long id) {
        return subjectService.findById(id);
    }

    @PostMapping(value = "/subjects/{id}")
    public void createSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
    }

    @PutMapping(value = "/subjects/{id}")
    public void updateSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
    }

    @DeleteMapping(value = "/subjects/{id}")
    public void deleteSubject(@PathVariable("id") Long id) {
        subjectService.deleteById(id);
    }

}

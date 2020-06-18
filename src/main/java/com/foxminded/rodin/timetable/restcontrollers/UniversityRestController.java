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

import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.service.UniversityService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class UniversityRestController {

    @Autowired
    private UniversityService universityService;

    @GetMapping(value = "/universities")
    public List<University> findAllUniversitys() {
        return universityService.findAll();
    }

    @GetMapping(value = "/universities/{id}")
    public University findUniversityById(@PathVariable("id") Long id) {
        return universityService.findById(id);
    }

    @PostMapping(value = "/universities/{id}")
    public void createUniversity(@RequestBody University university) {
        universityService.save(university);
    }

    @PutMapping(value = "/universities/{id}")
    public void updateUniversity(@RequestBody University university) {
        universityService.save(university);
    }

    @DeleteMapping(value = "/universities/{id}")
    public void deleteUniversity(@PathVariable("id") Long id) {
        universityService.deleteById(id);
    }

}

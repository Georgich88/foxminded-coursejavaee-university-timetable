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

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.service.FacultyService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/facultys")
    public List<Faculty> findAllFacultys() {
        return facultyService.findAll();
    }

    @GetMapping(value = "/facultys/{id}")
    public Faculty findFacultyById(@PathVariable("id") Long id) {
        return facultyService.findById(id);
    }

    @PostMapping(value = "/facultys/{id}")
    public void createFaculty(@RequestBody Faculty faculty) {
        facultyService.save(faculty);
    }

    @PutMapping(value = "/facultys/{id}")
    public void updateFaculty(@RequestBody Faculty faculty) {
        facultyService.save(faculty);
    }

    @DeleteMapping(value = "/facultys/{id}")
    public void deleteFaculty(@PathVariable("id") Long id) {
        facultyService.deleteById(id);
    }

}

package com.foxminded.rodin.timetable.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.service.FacultyService;
import com.foxminded.rodin.timetable.service.GroupService;

@Controller
public class FacultyController {

    private static final String FACULTY_FORM_RESOURSE_NAME = "faculty";
    private static final String FACULTIES_LIST_FORM_RESOURSE_NAME = "faculties";

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "/faculties")
    public String allFaculties(Model model) {

        Iterable<Faculty> faculties = facultyService.findAll();

        model.addAttribute("faculties", faculties);
        model.addAttribute("activeAll", true);

        return FACULTIES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/faculties/new")
    public String addNewFaculty(Model model, Principal principal) {
        Faculty faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        return FACULTY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/faculties/{id}/save")
    public String saveFaculty(@ModelAttribute("faculty") Faculty faculty) {
        groupService.saveAll(faculty.getGroups());
        facultyService.save(faculty);
        return "redirect:/" + FACULTIES_LIST_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/faculties/{id}/delete")
    public String deleteFaculty(@PathVariable("id") Long id) {
        facultyService.deleteById(id);
        return "redirect:/" + FACULTIES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/faculties/{id}")
    public String editFaculty(@PathVariable("id") Long id, Model model) {
        Faculty faculty = facultyService.findById(id);
        model.addAttribute("faculty", faculty);
        return FACULTY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/faculties/{id}/add-group")
    public String addGroup(Faculty faculty) {
        faculty.addNewGroup("");
        return FACULTY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/faculties/{id}/remove-group", params = { "rowId" })
    public String removeGroup(@RequestParam("rowId") int rowId, Faculty faculty) {
        faculty.getGroups().remove(rowId);
        return FACULTY_FORM_RESOURSE_NAME;
    }

}

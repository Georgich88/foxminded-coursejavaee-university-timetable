package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.service.FacultyService;
import com.foxminded.rodin.timetable.service.UniversityService;

@Controller
public class UniversityController {

    private static final String UNIVERSITY_FORM_RESOURSE_NAME = "university";
    private static final String UNIVERSITIES_LIST_FORM_RESOURSE_NAME = "universities";

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/universities")
    public String allUniversities(Model model) {

        List<University> universities = universityService.findAll();

        model.addAttribute("universities", universities);
        model.addAttribute("activeAll", true);

        return UNIVERSITIES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/universities/new")
    public String createUniversity(Model model, Principal principal) {
        University university = new University();
        model.addAttribute("university", university);
        return UNIVERSITY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/universities/{id}/save")
    public String saveUniversity(@ModelAttribute("university") University university) {
        facultyService.saveAll(university.getFaculties());
        universityService.save(university);
        return "redirect:/" + UNIVERSITIES_LIST_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/universities/{id}/delete")
    public String deleteUniversity(@PathVariable("id") Long id) {
        universityService.deleteById(id);
        return "redirect:/" + UNIVERSITIES_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/universities/{id}")
    public String editUniversity(@PathVariable("id") Long id, Model model) {
        University university = universityService.findById(id);
        model.addAttribute("university", university);
        return UNIVERSITY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/universities/{id}/add-faculty")
    public String addFaculty(University university) {
        university.addNewFaculty("");
        return UNIVERSITY_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/universities/{id}/remove-faculty", params = { "rowId" })
    public String removeFaculty(@RequestParam("rowId") int rowId, University university) {
        university.getFaculties().remove(rowId);
        return UNIVERSITY_FORM_RESOURSE_NAME;
    }

}

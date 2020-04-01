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

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.service.CourseService;
import com.foxminded.rodin.timetable.service.GroupService;

@Controller
public class GroupController {

    private static final String GROUP_FORM_RESOURSE_NAME = "group";
    private static final String GROUPS_LIST_FORM_RESOURSE_NAME = "groups";

    @Autowired
    private GroupService groupService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/groups")
    public String getAllGroups(Model model, Principal principal) {

        List<Group> groups = groupService.findAll();

        model.addAttribute("groups", groups);
        model.addAttribute("activeAll", true);

        return GROUPS_LIST_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/groups/new")
    public String showNewGroupForm(Model model, Principal principal) {
        Group group = new Group();
        model.addAttribute("group", group);
        return GROUP_FORM_RESOURSE_NAME;
    }

    @GetMapping(value = "/groups/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return GROUP_FORM_RESOURSE_NAME;
    }

    @PostMapping(value = "/groups/{id}/save")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/" + GROUPS_LIST_FORM_RESOURSE_NAME;
    }

    @ModelAttribute("requiredCourses")
    public List<Course> populateVarietiesCourses(Group group, Model model) {
        return courseService.findAll();
    }

}

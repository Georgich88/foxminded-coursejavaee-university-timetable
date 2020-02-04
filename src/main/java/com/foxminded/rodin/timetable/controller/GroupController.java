package com.foxminded.rodin.timetable.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.service.GroupService;

@Controller
public class GroupController {

    private static final String GROUPS_LIST_FORM_RESOURSE_NAME = "groups";

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public String getAllGroups(Model model, Principal principal) {

        List<Group> groups = groupService.findAll();

        model.addAttribute("groups", groups);
        model.addAttribute("activeAll", true);

        return GROUPS_LIST_FORM_RESOURSE_NAME;
    }

}

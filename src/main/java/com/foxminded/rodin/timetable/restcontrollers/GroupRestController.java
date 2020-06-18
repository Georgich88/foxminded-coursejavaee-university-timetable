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

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.service.GroupService;

@RestController
@RequestMapping("/api/${timetable.rest.version}")
public class GroupRestController {

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "/groups")
    public List<Group> findAllGroups() {
        return groupService.findAll();
    }

    @GetMapping(value = "/groups/{id}")
    public Group findGroupById(@PathVariable("id") Long id) {
        return groupService.findById(id);
    }

    @PostMapping(value = "/groups/{id}")
    public void createGroup(@RequestBody Group group) {
        groupService.save(group);
    }

    @PutMapping(value = "/groups/{id}")
    public void updateGroup(@RequestBody Group group) {
        groupService.save(group);
    }

    @DeleteMapping(value = "/groups/{id}")
    public void deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteById(id);
    }

}

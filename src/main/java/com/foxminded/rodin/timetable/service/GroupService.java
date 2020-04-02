package com.foxminded.rodin.timetable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.controller.exceptions.ElementNotFoundException;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.repo.GroupRepository;
import com.sun.istack.NotNull;

@Service
public class GroupService {

    private static final String ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID = "Cannot find a group by id=%d";

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        return groups;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group findById(@NonNull Long id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE_CANNOT_FIND_BY_ID, id);
            return new ElementNotFoundException(errorMessage);
        });
    }

    public List<Group> saveAll(List<Group> groups) {
        return (List<Group>) groupRepository.saveAll(groups);

    }

    public List<Group> findByStudenstId(@NotNull Long id) {
        List<Group> groups;
        if (id != null) {
            groups = groupRepository.findByStudenstId(id);
        } else {
            groups = new ArrayList<Group>();
        }
        return groups;
    }

}

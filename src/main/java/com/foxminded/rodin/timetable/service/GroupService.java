package com.foxminded.rodin.timetable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.repo.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {
        List<Group> groups = (List<Group>) groupRepository.findAll();
        return groups;
    }

    public List<Group> saveAll(List<Group> groups) {
        return (List<Group>) groupRepository.saveAll(groups);

    }

    public List<Group> findByStudenstId(Long id) {
        List<Group> groups;
        if (id != null) {
            groups = groupRepository.findByStudenstId(id);
        } else {
            groups = new ArrayList<Group>();
        }
        return groups;
    }

}

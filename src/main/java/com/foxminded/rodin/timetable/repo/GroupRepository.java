package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.organization.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {

}

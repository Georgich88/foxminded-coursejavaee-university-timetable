package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.organization.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {

}

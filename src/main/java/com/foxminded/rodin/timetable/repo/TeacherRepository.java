package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.people.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}

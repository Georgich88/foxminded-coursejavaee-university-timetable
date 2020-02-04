package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.people.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}

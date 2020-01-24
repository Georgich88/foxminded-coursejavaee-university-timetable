package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.curriculums.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}

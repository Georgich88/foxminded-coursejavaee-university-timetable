package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.curriculums.CourseSection;

public interface CourseSectionRepository extends CrudRepository<CourseSection, Long> {

}

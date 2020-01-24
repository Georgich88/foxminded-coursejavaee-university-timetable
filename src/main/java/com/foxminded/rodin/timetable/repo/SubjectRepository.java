package com.foxminded.rodin.timetable.repo;

import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.curriculums.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

}

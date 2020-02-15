package com.foxminded.rodin.timetable.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.curriculums.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    static final String DELETION_SUBJECTS_COURSES_BY_COURSE_ID_QUERY_TEMPLATE = "DELETE FROM subjects_courses WHERE courses_id = ?1";

    @Modifying
    @Query(value = DELETION_SUBJECTS_COURSES_BY_COURSE_ID_QUERY_TEMPLATE, nativeQuery = true)
    void deleteSubjectCoursesByCourseId(Long id);

}

package com.foxminded.rodin.timetable.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.people.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

    static final String DELETION_GROUP_STUDENTS_BY_STUDENT_ID_QUERY_TEMPLATE = "DELETE FROM groups_students WHERE students_id = ?1";

    @Modifying
    @Query(value = DELETION_GROUP_STUDENTS_BY_STUDENT_ID_QUERY_TEMPLATE, nativeQuery = true)
    void deleteGroupStudentsByStudentId(Long id);

}

package com.foxminded.rodin.timetable.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.organization.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {

    static final String SELECTION_BY_STUDENT_ID_QUERY_TEMPLATE = "SELECT groups.* AS id FROM groups_students LEFT JOIN groups ON group_id = groups.id WHERE students_id = ?1";

    @Query(value = SELECTION_BY_STUDENT_ID_QUERY_TEMPLATE, nativeQuery = true)
    List<Group> findByStudenstId(Long id);
}

package com.foxminded.rodin.timetable.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.model.schedules.Slot;

public interface SlotRepository extends CrudRepository<Slot, Long> {

    @Query(value = "SELECT r FROM Room r LEFT JOIN Slot s ON s.room = r  WHERE s.id IS NULL OR NOT (s.id != ?1 AND s.startTime >= ?2 AND s.endTime < ?3)")
    public List<Room> findAvailableRooms(Long id, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT g FROM Group g LEFT JOIN Slot s ON s.group = g  WHERE s.id IS NULL OR NOT (s.id != ?1 AND s.startTime >= ?2 AND s.endTime < ?3)")
    public List<Group> findAvailableGroups(Long id, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT t FROM Teacher t LEFT JOIN Slot s ON s.instructor = t  WHERE s.id IS NULL OR NOT (s.id != ?1 AND s.startTime >= ?2 AND s.endTime < ?3)")
    public List<Teacher> findAvailableTeachers(Long id, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT s FROM Slot s INNER JOIN Group g ON g = s.group INNER JOIN g.students st WHERE st.id = ?1")
    public List<Slot> findSlotsByStudentId(Long id);

}

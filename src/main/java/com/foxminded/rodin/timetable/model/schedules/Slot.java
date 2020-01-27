package com.foxminded.rodin.timetable.model.schedules;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.foxminded.rodin.timetable.model.curriculums.CourseSection;
import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.people.Teacher;

@Entity
@DynamicUpdate
@Table(name = "slots")
public class Slot {

    private final static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private CourseSection courseSection;
    @OneToOne
    private Teacher instructor;
    @OneToOne
    private Group group;
    @OneToOne
    private Room room;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime endTime;
    private String name;

    public Slot() {
    }

    public Slot(Teacher instructor, Group group, Room room, LocalDateTime startTime, LocalDateTime endTime) {
        this.instructor = instructor;
        this.group = group;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }

}

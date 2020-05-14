package com.foxminded.rodin.timetable.model.organization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.model.schedules.Plannable;

@Entity
@DynamicUpdate
@Table(name = "groups")
public class Group implements Plannable {

    private static final String MESSAGE_ERROR_EMPTY_STUDENT_LIST = "Group should have at least one student";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Size(min = 1, message = MESSAGE_ERROR_EMPTY_STUDENT_LIST)
    @ManyToMany
    private List<Student> students;
    @ManyToMany
    private List<Course> requriedCourses;

    public Group() {

    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<Student>();
        this.requriedCourses = new ArrayList<Course>();
    }

    public Group(String name) {
        this.name = name;
        this.students = new ArrayList<Student>();
        this.requriedCourses = new ArrayList<Course>();
    }

    public Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
        this.requriedCourses = new ArrayList<Course>();
    }

    public boolean isAvailable(LocalDate begin, LocalDate end) {
        return false;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getRequriedCourses() {
        return requriedCourses;
    }

    public void setRequriedCourses(List<Course> requriedCourses) {
        this.requriedCourses = requriedCourses;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", students=" + students + ", requried courses=" + requriedCourses
                + "]";
    }

}

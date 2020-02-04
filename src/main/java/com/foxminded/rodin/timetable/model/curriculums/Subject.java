package com.foxminded.rodin.timetable.model.curriculums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Course> courses;

    public Subject() {
        this.courses = new ArrayList<Course>();
        this.name = "";
    }

    public Subject(Long id, String name) {
        this.id = id;
        this.courses = new ArrayList<Course>();
        this.name = name;
    }

    public Subject(String name) {
        this.courses = new ArrayList<Course>();
        this.name = name;
    }

    public Course addNewCourse(String name, String description) {
        Course course = new Course(name, description);
        this.courses.add(course);
        return course;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Subject [id=" + id + ", name=" + name + ", courses=" + courses + "]";
    }

}

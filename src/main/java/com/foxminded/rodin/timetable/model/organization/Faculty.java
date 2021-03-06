package com.foxminded.rodin.timetable.model.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

import com.foxminded.rodin.timetable.model.curriculums.Subject;

@Entity
@DynamicUpdate
@Table(name = "faculties")
public class Faculty {

    private static final String ERROR_FACULTY_NAME_IS_MANDATORY_MESSAGE = "Faculty name is mandatory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = ERROR_FACULTY_NAME_IS_MANDATORY_MESSAGE)
    private String name;
    @OneToMany
    private List<Group> groups;
    @OneToMany
    private List<Subject> subjects;

    public Faculty() {
        this.name = "";
        this.groups = new ArrayList<Group>();
        this.subjects = new ArrayList<Subject>();
    }

    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
        this.groups = new ArrayList<Group>();
        this.subjects = new ArrayList<Subject>();
    }

    public Faculty(String name) {
        this.name = name;
        this.groups = new ArrayList<Group>();
        this.subjects = new ArrayList<Subject>();
    }

    public Group addNewGroup(String groupName) {
        Group group = new Group(groupName);
        this.groups.add(group);
        return group;
    }

    public Subject addNewSubject(String subjectName) {
        Subject subject = new Subject(subjectName);
        this.subjects.add(subject);
        return subject;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Faculty [id=" + id + ", name=" + name + ", groups=" + groups + ", subjects=" + subjects + "]";
    }

}

package com.foxminded.rodin.timetable.model.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "universites")
public class University {

    private static final String MESSAGE_ERROR_UNIVERSITY_NAME_IS_MANDATORY = "University name is mandatory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = MESSAGE_ERROR_UNIVERSITY_NAME_IS_MANDATORY)
    private String name;
    @ManyToMany
    private List<Faculty> faculties;

    public University() {
        this.name = "";
        this.faculties = new ArrayList<Faculty>();
    }

    public University(Long id, String name) {
        this.id = id;
        this.name = name;
        this.faculties = new ArrayList<Faculty>();
    }

    public University(String name) {
        this.name = name;
        this.faculties = new ArrayList<Faculty>();
    }

    public Faculty addNewFaculty(String facultyName) {
        Faculty faculty = new Faculty(facultyName);
        this.faculties.add(faculty);
        return faculty;
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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public String toString() {
        return "University [id=" + id + ", name=" + name + ", faculties=" + faculties + "]";
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

}

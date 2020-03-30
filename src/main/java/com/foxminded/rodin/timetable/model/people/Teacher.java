package com.foxminded.rodin.timetable.model.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "teacher")
public class Teacher extends Person {

    @Column
    private String academicRank;

    public Teacher() {
    }

    public Teacher(String firstName, String middleName, String lastName, String academicRank) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.academicRank = academicRank;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    @Override
    public String toString() {
        return academicRank + " " + getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

}

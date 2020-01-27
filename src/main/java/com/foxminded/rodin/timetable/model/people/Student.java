package com.foxminded.rodin.timetable.model.people;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "students")
public class Student extends Person {

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

}

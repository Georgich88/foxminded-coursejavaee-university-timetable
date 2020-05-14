package com.foxminded.rodin.timetable.model.people;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Person {

    private static final String MESSAGE_ERROR_FIRST_NAME_IS_MANDATORY = "First name is mandatory";
    private static final String MESSAGE_ERROR_LAST_NAME_IS_MANDATORY = "Last name is mandatory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = MESSAGE_ERROR_FIRST_NAME_IS_MANDATORY)
    private String firstName;
    private String middleName;
    @NotBlank(message = MESSAGE_ERROR_LAST_NAME_IS_MANDATORY)
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

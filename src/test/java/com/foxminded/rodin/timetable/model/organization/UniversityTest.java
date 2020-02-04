package com.foxminded.rodin.timetable.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UniversityTest {

    @Test
    public void shouldCreateUniversities() {

        University coloradoUniversity = new University(1L, "‎Colorado University");
        University princetonUniversity = new University(1L, "Princeton");

        List<University> universities = List.of(coloradoUniversity, princetonUniversity);

        String expectedResult = "[University [id=1, name=‎Colorado University, universities=[]], University [id=1, name=Princeton, universities=[]]]";

        assertEquals(expectedResult, universities.toString());

    }

}

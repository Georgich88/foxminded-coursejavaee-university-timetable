package com.foxminded.rodin.timetable.model.curriculums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void shouldCreateSubjects() {

        var clinicalPsychologySubject = new Subject(1L, "Clinical Psychology");
        var socialPsycologySubject = new Subject(2L, "Social Psychology");

        assertEquals(
                "[Subject [id=1, name=Clinical Psychology, courses=[]], Subject [id=2, name=Social Psychology, courses=[]]]",
                List.of(clinicalPsychologySubject, socialPsycologySubject).toString());
    }

}

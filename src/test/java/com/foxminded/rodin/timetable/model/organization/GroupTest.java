package com.foxminded.rodin.timetable.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.rodin.timetable.model.people.Student;

public class GroupTest {

    @Test
    public void shouldCreateGroups() {

        var psycologyGroup = new Group(1L, "‎PSY-01-01");
        var computerScienceGroup = new Group(1L, "‎‎CS-01-01");

        var faculties = List.of(psycologyGroup, computerScienceGroup);

        String expectedResult = "[Group [id=1, name=‎PSY-01-01, students=[], requried courses=[]], Group [id=1, name=‎‎CS-01-01, students=[], requried courses=[]]]";

        assertEquals(expectedResult, faculties.toString());

    }

    @Test
    public void shouldAssignStudentsToGroup() {

        var psycologyGroup = new Group(1L, "‎PSY-01-01");
        var studentJon = new Student("Jon", "Bones", "Jones");
        var studentAnderson = new Student("Anderson", "Spider", "Silva");

        var psycologyStudents = List.of(studentJon, studentAnderson);
        psycologyGroup.setStudents(List.of(studentJon, studentAnderson));

        assertIterableEquals(psycologyStudents, psycologyGroup.getStudents());

    }

}

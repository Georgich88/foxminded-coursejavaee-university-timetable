package com.foxminded.rodin.timetable.model.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.rodin.timetable.demodatageneration.DataGenerator;

public class TeacherTests {

    @Test
    public void shouldCreateTeachers() {

        List<Teacher> teachers = DataGenerator.generateDemoInstructors();
        assertEquals("[proffesor Dana Jon White, dean Herb  Dean]", teachers.toString());

    }

}

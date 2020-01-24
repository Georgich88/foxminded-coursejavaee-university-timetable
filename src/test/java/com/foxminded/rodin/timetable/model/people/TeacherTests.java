package com.foxminded.rodin.timetable.model.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.foxminded.rodin.timetable.application.DataGenerator;

class TeacherTests {

	@Test
	void shouldCreateTeachers() {

		var teachers = DataGenerator.generateDemoInstructors();
		assertEquals("[proffesor Dana Jon White, dean Herb  Dean]", teachers.toString());

	}

}

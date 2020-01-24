package com.foxminded.rodin.timetable.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class UniversityTest {

	@Test
	void shouldCreateUniversities() {

		var coloradoUniversity = new University(1L, "‎Colorado University");
		var princetonUniversity = new University(1L, "Princeton");

		var faculties = List.of(coloradoUniversity, princetonUniversity);

		String expectedResult = "[University [id=1, name=‎Colorado University, faculties=[]], University [id=1, name=Princeton, faculties=[]]]";

		assertEquals(expectedResult, faculties.toString());

	}

}

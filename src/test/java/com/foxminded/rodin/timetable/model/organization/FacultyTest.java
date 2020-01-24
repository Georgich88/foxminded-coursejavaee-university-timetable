package com.foxminded.rodin.timetable.model.organization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class FacultyTest {

	@Test
	void shouldCreateFaculties() {

		var psycologyFaculty = new Faculty(1L, "‎Department of Psychology");
		var computerScienceFaculty = new Faculty(1L, "‎‎Department of Computer Science");

		var faculties = List.of(psycologyFaculty, computerScienceFaculty);

		String expectedResult = "[Faculty [id=1, name=‎Department of Psychology, groups=[], subjects=[]], Faculty [id=1, name=‎‎Department of Computer Science, groups=[], subjects=[]]]";

		assertEquals(expectedResult, faculties.toString());

	}

}

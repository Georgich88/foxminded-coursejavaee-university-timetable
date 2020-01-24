package com.foxminded.rodin.timetable.model.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.foxminded.rodin.timetable.application.DataGenerator;

class StudentTest {

	@Test
	void shouldCreateStudents() {

		var psycologyStudents = DataGenerator.generateDemoPsycologyStudents();
		assertEquals(
				"[Jon Bones Jones, Anderson Spider Silva, Georges Rush St-Pierre, Conor Notorious McGregor, Royce  Gracie, Ken  Shamrock, Brock  Lesnar, B.J. Prodigy Penn]",
				psycologyStudents.toString());

	}

}

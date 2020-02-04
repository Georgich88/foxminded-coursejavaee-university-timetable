package com.foxminded.rodin.timetable.model.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.rodin.timetable.demodatageneration.DataGenerator;

public class StudentTest {

    @Test
    public void shouldCreateStudents() {

        List<Student> psycologyStudents = DataGenerator.generateDemoPsycologyStudents();
        assertEquals(
                "[Jon Bones Jones, Anderson Spider Silva, Georges Rush St-Pierre, Conor Notorious McGregor, Royce  Gracie, Ken  Shamrock, Brock  Lesnar, B.J. Prodigy Penn]",
                psycologyStudents.toString());

    }

}

package com.foxminded.rodin.timetable.model.curriculums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    public void shouldCreateCourse() {

        var psychotherapyResearchCourse = new Course(1L, "PSY 2445 Psychotherapy Research",
                "Psychotherapy Research is a bimonthly peer-reviewed academic journal covering research in all fields of psychotherapy: outcome, process, education and training of therapists, as well as investigations of services.");

        psychotherapyResearchCourse.addNewSection("Psychotherapy Research Introduction Lecture", "", "", 60);
        psychotherapyResearchCourse.addNewSection("Psychotherapy Research Lecture 1", "", "", 60);
        psychotherapyResearchCourse.addNewSection("Practise", "", "", 60);

        assertEquals(
                "Course [id=1, name=PSY 2445 Psychotherapy Research, description=Psychotherapy Research is a bimonthly peer-reviewed academic journal covering research in all fields of psychotherapy: outcome, process, education and training of therapists, as well as investigations of services., sections=[CourseSection [id=null, name=Psychotherapy Research Introduction Lecture, notes=, grading basis=, time required=60], CourseSection [id=null, name=Psychotherapy Research Lecture 1, notes=, grading basis=, time required=60], CourseSection [id=null, name=Practise, notes=, grading basis=, time required=60]]]",
                psychotherapyResearchCourse.toString());

    }

}

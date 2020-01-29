package com.foxminded.rodin.timetable.demodatageneration;

import java.util.List;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.model.people.Teacher;

public class DataGenerator {

    public static University generateDemoUniversity() {

        University caltech = new University("‎Caltech");

        List<Student> psycologyStudents = DataGenerator.generateDemoPsycologyStudents();
        List<Student> csStudents = DataGenerator.generateDemoComputerScienceStudents();

        Faculty psycology = caltech.addNewFaculty("‎Department of Psychology");
        Subject clinicalPsychologySubject = psycology.addNewSubject("Clinical Psychology");
        Subject socialPsycologySubject = psycology.addNewSubject("Social Psychology");
        psycology.addNewGroup("PSY-01-01").setStudents(psycologyStudents);
        DataGenerator.generateDemoClinicalPsychologyCourses(clinicalPsychologySubject);

        Faculty computerScience = caltech.addNewFaculty("‎‎Department of Computer Science");
        Subject mathSubject = computerScience.addNewSubject("Math");
        Subject dataStructuresSubject = computerScience.addNewSubject("Data Structures");
        computerScience.addNewGroup("CS-01-01").setStudents(csStudents);

        return caltech;
    }

    public static Building generateDemoBuilding() {
        Building mainBuilding = new Building("Main building");
        mainBuilding.addNewRoom("F1 - 101", 20);
        mainBuilding.addNewRoom("F1 - 102", 50);
        mainBuilding.addNewRoom("F2 - 201", 40);
        mainBuilding.addNewRoom("F2 - 202", 30);
        mainBuilding.addNewRoom("F2 - 203", 10);
        return mainBuilding;
    }

    public static List<Teacher> generateDemoInstructors() {
        return List.of(new Teacher("Dana", "Jon", "White", "proffesor"), new Teacher("Herb", "", "Dean", "dean"));
    }

    public static List<Student> generateDemoComputerScienceStudents() {
        List<Student> csStudents = List.of(new Student("Mike", "Iron", "Tyson"), new Student("Oscar", "De La", "Hoya"),
                new Student("Floyd", "Joy", "Mayweather"), new Student("Evander ", "", "Holyfield "),
                new Student("George", "Edward", "Foreman"), new Student("William", "Harrison", "Dempsey"),
                new Student("Joseph", "Louis", "Barrow"), new Student("Emmanuel", "Dapidran", "Pacquiao"));
        return csStudents;
    }

    public static List<Student> generateDemoPsycologyStudents() {
        List<Student> psycologyStudents = List.of(new Student("Jon", "Bones", "Jones"),
                new Student("Anderson", "Spider", "Silva"), new Student("Georges", "Rush", "St-Pierre"),
                new Student("Conor", "Notorious", "McGregor"), new Student("Royce", "", "Gracie"),
                new Student("Ken", "", "Shamrock"), new Student("Brock", "", "Lesnar"),
                new Student("B.J.", "Prodigy", "Penn"));
        return psycologyStudents;
    }

    public static void generateDemoClinicalPsychologyCourses(Subject clinicalPsychologySubject) {

        Course psychotherapyResearchCourse = clinicalPsychologySubject.addNewCourse("PSY 2445 Psychotherapy Research",
                "Psychotherapy Research is a bimonthly peer-reviewed academic journal covering research in all fields of psychotherapy: outcome, process, education and training of therapists, as well as investigations of services.");

        psychotherapyResearchCourse.addNewSection("Psychotherapy Research Introduction Lecture", "", "", 60);
        psychotherapyResearchCourse.addNewSection("Psychotherapy Research Lecture 1", "", "", 60);
        psychotherapyResearchCourse.addNewSection("Psychotherapy Research Lecture 2", "", "", 60);
        psychotherapyResearchCourse.addNewSection("Practise", "", "", 60);

        clinicalPsychologySubject.addNewCourse("PSY 3800 Psychometric Theory",
                "Psychometrics is a field of study concerned with the theory and technique of psychological measurement. ");

    }

}

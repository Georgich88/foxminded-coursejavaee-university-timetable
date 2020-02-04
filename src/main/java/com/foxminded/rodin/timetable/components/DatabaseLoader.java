package com.foxminded.rodin.timetable.components;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.foxminded.rodin.timetable.demodatageneration.DataGenerator;
import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.model.curriculums.CourseSection;
import com.foxminded.rodin.timetable.model.curriculums.Subject;
import com.foxminded.rodin.timetable.model.facilities.Building;
import com.foxminded.rodin.timetable.model.organization.Faculty;
import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.model.organization.University;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.model.people.Teacher;
import com.foxminded.rodin.timetable.repo.BuildingRepository;
import com.foxminded.rodin.timetable.repo.CourseRepository;
import com.foxminded.rodin.timetable.repo.CourseSectionRepository;
import com.foxminded.rodin.timetable.repo.FacultyRepository;
import com.foxminded.rodin.timetable.repo.GroupRepository;
import com.foxminded.rodin.timetable.repo.RoomRepository;
import com.foxminded.rodin.timetable.repo.StudentRepository;
import com.foxminded.rodin.timetable.repo.SubjectRepository;
import com.foxminded.rodin.timetable.repo.TeacherRepository;
import com.foxminded.rodin.timetable.repo.UniversityRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseSectionRepository courseSectionRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Teacher> instructors = DataGenerator.generateDemoInstructors();
        University caltech = DataGenerator.generateDemoUniversity();
        Building mainBuilding = DataGenerator.generateDemoBuilding();
        List<Faculty> faculties = caltech.getFaculties();

        Set<Group> groups = new HashSet<Group>();
        Set<Subject> subjects = new HashSet<Subject>();
        Set<Student> students = new HashSet<Student>();
        Set<Course> courses = new HashSet<Course>();
        Set<CourseSection> courseSections = new HashSet<CourseSection>();

        faculties.forEach(faculty -> {

            List<Group> facultyGroups = faculty.getGroups();
            List<Subject> facultySubjects = faculty.getSubjects();

            groups.addAll(facultyGroups);
            subjects.addAll(facultySubjects);

            facultyGroups.forEach(g -> {
                students.addAll(g.getStudents());
            });

            facultySubjects.forEach(s -> {
                List<Course> subjectCourses = s.getCourses();
                courses.addAll(subjectCourses);
                subjectCourses.forEach(c -> {
                    courseSections.addAll(c.getSections());
                });
            });

        });

        this.studentRepository.saveAll(students);
        this.groupRepository.saveAll(groups);
        this.courseSectionRepository.saveAll(courseSections);
        this.courseRepository.saveAll(courses);
        this.subjectRepository.saveAll(subjects);
        this.facultyRepository.saveAll(faculties);
        this.universityRepository.save(caltech);
        this.teacherRepository.saveAll(instructors);
        this.roomRepository.saveAll(mainBuilding.getRooms());
        this.buildingRepository.save(mainBuilding);

    }

}

package com.foxminded.rodin.timetable.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

	private final UniversityRepository universityRepository;
	private final StudentRepository studentRepository;
	private final FacultyRepository facultyRepository;
	private final GroupRepository groupRepository;
	private final TeacherRepository teacherRepository;
	private final SubjectRepository subjectRepository;
	private final CourseRepository courseRepository;
	private final CourseSectionRepository courseSectionRepository;
	private final BuildingRepository buildingRepository;
	private final RoomRepository roomRepository;

	@Autowired
	public DatabaseLoader(UniversityRepository universityRepository, StudentRepository studentRepository,
			FacultyRepository facultyRepository, GroupRepository groupRepository, TeacherRepository teacherRepository,
			SubjectRepository subjectRepository, CourseRepository courseRepository,
			CourseSectionRepository courseSectionRepository, BuildingRepository buildingRepository,
			RoomRepository roomRepository) {
		this.studentRepository = studentRepository;
		this.universityRepository = universityRepository;
		this.facultyRepository = facultyRepository;
		this.groupRepository = groupRepository;
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
		this.courseRepository = courseRepository;
		this.courseSectionRepository = courseSectionRepository;
		this.buildingRepository = buildingRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		var instructors = DataGenerator.generateDemoInstructors();
		var caltech = DataGenerator.generateDemoUniversity();
		var mainBuilding = DataGenerator.generateDemoBuilding();
		var faculties = caltech.getFaculties();

		faculties.forEach(faculty -> {

			var groups = faculty.getGroups();
			var subjects = faculty.getSubjects();

			groups.forEach(group -> {
				this.studentRepository.saveAll(group.getStudents());
			});
			this.groupRepository.saveAll(groups);

			subjects.forEach(subject -> {
				var courses = subject.getCourses();
				courses.forEach(course -> {
					this.courseSectionRepository.saveAll(course.getSections());
				});
				this.courseRepository.saveAll(courses);
			});
			this.subjectRepository.saveAll(subjects);
		});
		this.facultyRepository.saveAll(faculties);
		this.universityRepository.save(caltech);
		this.teacherRepository.saveAll(instructors);

		this.roomRepository.saveAll(mainBuilding.getRooms());
		this.buildingRepository.save(mainBuilding);

	}

}

package com.foxminded.rodin.timetable.model.organization;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.foxminded.rodin.timetable.model.curriculums.Course;
import com.foxminded.rodin.timetable.model.people.Student;
import com.foxminded.rodin.timetable.model.schedules.Plannable;

@Entity
@DynamicUpdate
@Table(name = "groups")
public class Group implements Plannable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany
	private List<Student> students;
	@ManyToMany
	private List<Course> requriedCourses;

	public Group() {

	}

	public Group(String name) {
		this.name = name;
	}

	public Group(String name, List<Student> students) {
		this.name = name;
		this.students = students;
	}

	public boolean isAvailable(LocalDate begin, LocalDate end) {
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getRequriedCourses() {
		return requriedCourses;
	}

	public void setRequriedCourses(List<Course> requriedCourses) {
		this.requriedCourses = requriedCourses;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", students=" + students + ", requriedCourses=" + requriedCourses
				+ "]";
	}

}

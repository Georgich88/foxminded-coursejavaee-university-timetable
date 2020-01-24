package com.foxminded.rodin.timetable.model.organization;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "universites")
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@ManyToMany
	private List<Faculty> faculties;

	public University() {
		this.name = "";
		this.faculties = new ArrayList<Faculty>();
	}

	public University(String name) {
		this.name = name;
		this.faculties = new ArrayList<Faculty>();
	}

	public Faculty addNewFaculty(String facultyName) {
		var faculty = new Faculty(facultyName);
		this.faculties.add(faculty);
		return faculty;
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

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

}

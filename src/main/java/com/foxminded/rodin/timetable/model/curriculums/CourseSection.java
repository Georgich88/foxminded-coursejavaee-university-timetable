package com.foxminded.rodin.timetable.model.curriculums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "sections")
public class CourseSection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String notes;
	private String gradingBasis;
	private int timeRequired;

	public CourseSection() {
		this.name = "";
		this.notes = "";
		this.gradingBasis = "";
	}

	public CourseSection(String name, String notes, String gradingBasis, int timeRequired) {
		this.name = name;
		this.notes = notes;
		this.gradingBasis = gradingBasis;
		this.timeRequired = timeRequired;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getGradingBasis() {
		return gradingBasis;
	}

	public void setGradingBasis(String gradingBasis) {
		this.gradingBasis = gradingBasis;
	}

	public int getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}

	@Override
	public String toString() {
		return "CourseSection [id=" + id + ", name=" + name + ", notes=" + notes + ", grading basis=" + gradingBasis
				+ ", time required=" + timeRequired + "]";
	}

}

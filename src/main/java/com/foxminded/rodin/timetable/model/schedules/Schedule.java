package com.foxminded.rodin.timetable.model.schedules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate
@Table(name = "schedules")
public class Schedule {

	private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany
	private List<Slot> slots;
	@DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
	private LocalDate startDate;
	@DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
	private LocalDate endDate;

	public Schedule() {
		this.name = "";
		this.slots = new ArrayList<Slot>();
	}

	public Schedule(String name, List<Slot> slots) {
		this.name = name;
		this.slots = slots;
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

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}

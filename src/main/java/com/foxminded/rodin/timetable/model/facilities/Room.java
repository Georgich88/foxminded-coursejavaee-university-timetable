package com.foxminded.rodin.timetable.model.facilities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.foxminded.rodin.timetable.model.schedules.Plannable;

@Entity
@DynamicUpdate
@Table(name = "rooms")
public class Room implements Plannable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int peopleCapacity;

	public Room() {
	}

	public Room(String name, int peopleCapacity) {
		this.name = name;
		this.peopleCapacity = peopleCapacity;
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

	public int getPeopleCapacity() {
		return peopleCapacity;
	}

	public void setPeopleCapacity(int peopleCapacity) {
		this.peopleCapacity = peopleCapacity;
	}

	@Override
	public String toString() {
		return "Room{" + "id='" + id + '\'' + ", people capacity=" + peopleCapacity + '}';
	}
}

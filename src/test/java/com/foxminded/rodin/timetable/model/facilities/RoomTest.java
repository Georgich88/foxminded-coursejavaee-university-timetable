package com.foxminded.rodin.timetable.model.facilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class RoomTest {

	@Test
	void shouldCreateRooms() {

		var lexureHall = new Room("lexure hall", 10);
		lexureHall.setId(1L);
		var practiseRoom = new Room("practise room", 10);
		lexureHall.setId(2L);

		var rooms = List.of(lexureHall, practiseRoom);

		String expectedResult = "[Room{id='2', people capacity=10}, Room{id='null', people capacity=10}]";

		assertEquals(expectedResult, rooms.toString());
	}

}

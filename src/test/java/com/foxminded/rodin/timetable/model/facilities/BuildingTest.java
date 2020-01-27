package com.foxminded.rodin.timetable.model.facilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BuildingTest {

    @Test
    public void shouldCreateBuilding() {

        List<Building> buildings = new ArrayList<Building>(4);

        var mainBuilding = new Building("Main building");
        mainBuilding.addNewRoom("lexure hall", 10).setId(1L);
        mainBuilding.addNewRoom("practise room", 10).setId(2L);

        var gymBuilding = new Building("Sport Gym");
        gymBuilding.addNewRoom("tennis hall", 10).setId(3L);
        gymBuilding.addNewRoom("boxing", 30).setId(4L);

        var dormitoryBuilding = new Building("Dormitory");
        gymBuilding.addNewRoom("Students room", 2).setId(5L);
        gymBuilding.addNewRoom("Staff room", 2).setId(6L);

        buildings = List.of(mainBuilding, gymBuilding, dormitoryBuilding);

        String expectedResult = "[Building{name='Main building', id='null', rooms=[Room{id='1', people capacity=10}, Room{id='2', people capacity=10}]}, Building{name='Sport Gym', id='null', rooms=[Room{id='3', people capacity=10}, Room{id='4', people capacity=30}, Room{id='5', people capacity=2}, Room{id='6', people capacity=2}]}, Building{name='Dormitory', id='null', rooms=[]}]";

        assertEquals(expectedResult, buildings.toString());
    }

}

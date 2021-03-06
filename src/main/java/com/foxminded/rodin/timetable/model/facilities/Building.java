package com.foxminded.rodin.timetable.model.facilities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "buildings")
public class Building {

    private static final String ERROR_EMPTY_ROOM_LIST_MESSAGE = "Building should have at least one room";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @NotNull
    @Size(min = 1, message = ERROR_EMPTY_ROOM_LIST_MESSAGE)
    private List<Room> rooms;

    public Building() {
        this.name = "";
        this.rooms = new ArrayList<Room>();
    }

    public Building(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
    }

    public Building(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public Room addNewRoom(String roomName, int peopleCapacity) {
        Room room = new Room(roomName, peopleCapacity);
        this.rooms.add(room);
        return room;
    }

    @Override
    public String toString() {
        return "Building{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", rooms=" + rooms + '}';
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}

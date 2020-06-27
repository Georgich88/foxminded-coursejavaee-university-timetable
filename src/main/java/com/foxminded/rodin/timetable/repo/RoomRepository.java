package com.foxminded.rodin.timetable.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.foxminded.rodin.timetable.model.facilities.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

    static final String DELETION_BUILDING_ROOMS_BY_ROOM_ID_QUERY_TEMPLATE = "DELETE FROM buildings_rooms WHERE rooms_id = ?1";

    @Modifying
    @Query(value = DELETION_BUILDING_ROOMS_BY_ROOM_ID_QUERY_TEMPLATE, nativeQuery = true)
    void deleteBuildingRoomsByRoomId(Long id);
}

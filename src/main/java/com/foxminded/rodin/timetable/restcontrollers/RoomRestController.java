package com.foxminded.rodin.timetable.restcontrollers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.rodin.timetable.model.facilities.Room;
import com.foxminded.rodin.timetable.service.RoomService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class RoomRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<EntityModel<Room>> findAllRooms() {
        return roomService.findAll().stream()
                .map(room -> toModel(room))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/rooms/{id}")
    public EntityModel<Room> findRoomById(@PathVariable("id") Long id) {
        return toModel(roomService.findById(id));
    }

    @PostMapping(value = "/rooms/{id}")
    public ResponseEntity<?> createRoom(@RequestBody Room room) {
        EntityModel<Room> entityModel = toModel(roomService.save(room));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/rooms/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody Room room) {
        EntityModel<Room> entityModel = toModel(roomService.save(room));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Room> toModel(Room room) {
        return EntityModel.of(room,
                linkTo(methodOn(RoomRestController.class, restVersion).findRoomById(room.getId())).withSelfRel(),
                linkTo(methodOn(RoomRestController.class, restVersion).findAllRooms()).withRel("rooms"));
    }

    public ResponseEntity<EntityModel<Room>> toResponse(EntityModel<Room> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

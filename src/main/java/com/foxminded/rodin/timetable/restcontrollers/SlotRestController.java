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

import com.foxminded.rodin.timetable.model.schedules.Slot;
import com.foxminded.rodin.timetable.service.SlotService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class SlotRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private SlotService slotService;

    @GetMapping(value = "/slots")
    public List<EntityModel<Slot>> findAllSlots() {
        return slotService.findAll().stream()
                .map(slot -> toModel(slot))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/slots/{id}")
    public EntityModel<Slot> findSlotById(@PathVariable("id") Long id) {
        return toModel(slotService.findById(id));
    }

    @PostMapping(value = "/slots/{id}")
    public ResponseEntity<?> createSlot(@RequestBody Slot slot) {
        EntityModel<Slot> entityModel = toModel(slotService.save(slot));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/slots/{id}")
    public ResponseEntity<?> updateSlot(@RequestBody Slot slot) {
        EntityModel<Slot> entityModel = toModel(slotService.save(slot));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/slots/{id}")
    public ResponseEntity<?> deleteSlot(@PathVariable("id") Long id) {
        slotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Slot> toModel(Slot slot) {
        return EntityModel.of(slot,
                linkTo(methodOn(SlotRestController.class, restVersion).findSlotById(slot.getId())).withSelfRel(),
                linkTo(methodOn(SlotRestController.class, restVersion).findAllSlots()).withRel("slots"));
    }

    public ResponseEntity<EntityModel<Slot>> toResponse(EntityModel<Slot> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

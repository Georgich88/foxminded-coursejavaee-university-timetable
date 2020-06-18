package com.foxminded.rodin.timetable.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/${timetable.rest.version}")
public class SlotRestController {

    @Autowired
    private SlotService slotService;

    @GetMapping(value = "/slots")
    public List<Slot> findAllSlots() {
        return (List<Slot>) slotService.findAll();
    }

    @GetMapping(value = "/slots/{id}")
    public Slot findSlotById(@PathVariable("id") Long id) {
        return slotService.findById(id);
    }

    @PostMapping(value = "/slots/{id}")
    public void createSlot(@RequestBody Slot slot) {
        slotService.save(slot);
    }

    @PutMapping(value = "/slots/{id}")
    public void updateSlot(@RequestBody Slot slot) {
        slotService.save(slot);
    }

    @DeleteMapping(value = "/slots/{id}")
    public void deleteSlot(@PathVariable("id") Long id) {
        slotService.deleteById(id);
    }

}

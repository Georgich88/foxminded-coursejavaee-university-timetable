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

import com.foxminded.rodin.timetable.model.organization.Group;
import com.foxminded.rodin.timetable.service.GroupService;

@RestController
@RequestMapping("/api/{timetable.rest.version}")
public class GroupRestController {

    @Value("${timetable.rest.version}")
    private String restVersion;

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "/groups")
    public List<EntityModel<Group>> findAllGroups() {
        return groupService.findAll().stream()
                .map(group -> toModel(group))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/groups/{id}")
    public EntityModel<Group> findGroupById(@PathVariable("id") Long id) {
        return toModel(groupService.findById(id));
    }

    @PostMapping(value = "/groups/{id}")
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        EntityModel<Group> entityModel = toModel(groupService.save(group));
        return toResponse(entityModel);
    }

    @PutMapping(value = "/groups/{id}")
    public ResponseEntity<?> updateGroup(@RequestBody Group group) {
        EntityModel<Group> entityModel = toModel(groupService.save(group));
        return toResponse(entityModel);
    }

    @DeleteMapping(value = "/groups/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Long id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public EntityModel<Group> toModel(Group group) {
        return EntityModel.of(group,
                linkTo(methodOn(GroupRestController.class, restVersion).findGroupById(group.getId())).withSelfRel(),
                linkTo(methodOn(GroupRestController.class, restVersion).findAllGroups()).withRel("groups"));
    }

    public ResponseEntity<EntityModel<Group>> toResponse(EntityModel<Group> entityModel) {
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}

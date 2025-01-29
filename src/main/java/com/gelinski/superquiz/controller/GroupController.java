package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.GroupDTO;
import com.gelinski.superquiz.model.Group;
import com.gelinski.superquiz.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
@Tag(name = "Group", description = "Group API")
public class GroupController {
    private final GroupService groupService;

    @Operation(summary = "Listagem de turmas", description = "Lista todas as turmas", tags = {"Group"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Group.class))}),
    })
    @GetMapping
    public ResponseEntity<List<Group>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @PostMapping
    public ResponseEntity<Group> save(@RequestBody GroupDTO group) {
        return ResponseEntity.ok(groupService.save(group));
    }

    @PutMapping
    public ResponseEntity<Group> edit(@RequestBody GroupDTO group) {
        return ResponseEntity.ok(groupService.edit(group));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<List<Group>> findById(@PathVariable Long teacherId) {
        return ResponseEntity.ok(groupService.findByTeacher(teacherId));
    }
}

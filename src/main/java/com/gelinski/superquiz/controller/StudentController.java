package com.gelinski.superquiz.controller;


import com.gelinski.superquiz.dto.StudentDTO;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.service.StudentService;
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

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Student API")
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "Listagem de alunos", description = "Lista todos os alunos", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))}),
    })
    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody StudentDTO student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @PutMapping
    public ResponseEntity<Student> edit(@RequestBody StudentDTO student) {
        return ResponseEntity.ok(studentService.edit(student));
    }
}

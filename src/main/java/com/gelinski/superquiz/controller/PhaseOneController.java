package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.dto.refactor.CreatePhaseOneRequest;
import com.gelinski.superquiz.model.Group;
import com.gelinski.superquiz.service.PhaseOneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phase-one")
@RequiredArgsConstructor
public class PhaseOneController {
    private final PhaseOneService service;

    @PostMapping("/answer/{studentId}")
    public ResponseEntity<Boolean> saveStudentQuestion(@RequestBody AnsweredQuestionDTO answer, @PathVariable Long studentId) {
        Boolean result;
        try {
            result = service.savePhaseOneAnswer(answer, studentId);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Criação de exercício da fase 1. NOVO")
    @PostMapping
    public ResponseEntity<Void> createExercise(@RequestBody CreatePhaseOneRequest request) {
        service.createExercise(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

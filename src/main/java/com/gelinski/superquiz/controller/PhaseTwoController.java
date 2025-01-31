package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.dto.refactor.CreatePhaseOneRequest;
import com.gelinski.superquiz.dto.refactor.CreatePhaseTwoRequest;
import com.gelinski.superquiz.model.Answer;
import com.gelinski.superquiz.service.PhaseTwoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phase-two")
@RequiredArgsConstructor
public class PhaseTwoController {
    private final PhaseTwoService service;

    @GetMapping("/questions")
    public ResponseEntity<List<Answer>> findAllQuestions() {
        return ResponseEntity.ok(service.findAllQuestions());
    }

    /*@PostMapping("/answer/{studentId}/{questionId}")
    public ResponseEntity<Boolean> saveStudentQuestion(@RequestBody AnsweredQuestionDTO answer, @PathVariable Long studentId, @PathVariable Long questionId) {
        Boolean result;
        try {
            result = service.saveStudentQuestion(answer, studentId, questionId);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(result);
    }*/

    @Operation(summary = "Criação de exercício da fase 2. NOVO")
    @PostMapping
    public ResponseEntity<Void> createExercise(@RequestBody CreatePhaseTwoRequest request) {
        service.createExercise(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Listagens de Palavras Relacionadas, para criação da fase 2. NOVO")
    @GetMapping("/words/{themeId}")
    public ResponseEntity<List<String>> getWords(@PathVariable Integer themeId) {
        return ResponseEntity.ok(service.getWords(themeId));
    }
}

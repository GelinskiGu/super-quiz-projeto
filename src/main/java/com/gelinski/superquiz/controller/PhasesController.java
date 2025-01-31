package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.refactor.RenderPhasesBaseResponse;
import com.gelinski.superquiz.service.PhasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phases")
@RequiredArgsConstructor
public class PhasesController {
    private final PhasesService phasesService;

    @GetMapping("/render")
    public ResponseEntity<RenderPhasesBaseResponse> renderPhase(@RequestParam Integer phaseNumber, @RequestParam Integer idTheme, @RequestParam Integer difficultyNumber) {
        return ResponseEntity.ok(phasesService.renderPhase(phaseNumber, idTheme, difficultyNumber));
    }
}

package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.refactor.ColorDTO;
import com.gelinski.superquiz.dto.refactor.RenderPhasesBaseResponse;
import com.gelinski.superquiz.service.PhasesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phases")
@RequiredArgsConstructor
public class PhasesController {
    private final PhasesService phasesService;

    @Operation(summary = "Renderização de fases. NOVO")
    @GetMapping("/render")
    public ResponseEntity<RenderPhasesBaseResponse> renderPhase(@RequestParam Integer phaseNumber, @RequestParam Integer idTheme, @RequestParam Integer difficultyNumber) {
        return ResponseEntity.ok(phasesService.renderPhase(phaseNumber, idTheme, difficultyNumber));
    }

    @Operation(summary = "Lista cores. NOVO")
    @GetMapping("/colors")
    public ResponseEntity<List<ColorDTO>> getColors() {
        return ResponseEntity.ok(phasesService.getColors());
    }

    @Operation(summary = "Cria cores. NOVO")
    @PostMapping("/colors")
    public ResponseEntity<Void> createColor(@RequestBody ColorDTO colorDTO) {
        phasesService.createColor(colorDTO);
        return ResponseEntity.ok().build();
    }
}

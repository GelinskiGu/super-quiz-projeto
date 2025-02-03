package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.refactor.CreateThemeRequest;
import com.gelinski.superquiz.dto.refactor.ThemeDTO;
import com.gelinski.superquiz.service.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theme")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @Operation(summary = "Listagem dos temas existentes, para o momento de renderizar as fases. NOVO")
    @GetMapping("/{studentId}")
    public ResponseEntity<List<ThemeDTO>> getThemesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(themeService.getThemes(studentId));
    }

    @Operation(summary = "Listagem dos temas existentes, para o professor. NOVO")
    @GetMapping("teacher/{teacherId}")
    public ResponseEntity<List<ThemeDTO>> getThemesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(themeService.getThemesByTeacher(teacherId));
    }

    @Operation(summary = "Criação de novos temas. NOVO")
    @PostMapping
    public ResponseEntity<Void> createTheme(@RequestBody CreateThemeRequest createThemeRequest) {
        themeService.createTheme(createThemeRequest);
        return ResponseEntity.ok().build();
    }
}

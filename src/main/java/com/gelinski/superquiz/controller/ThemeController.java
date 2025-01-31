package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.refactor.CreateThemeRequest;
import com.gelinski.superquiz.dto.refactor.ThemeDTO;
import com.gelinski.superquiz.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theme")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<ThemeDTO>> getThemesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(themeService.getThemes(studentId));
    }

    @PostMapping
    public ResponseEntity<Void> createTheme(@RequestBody CreateThemeRequest createThemeRequest) {
        themeService.createTheme(createThemeRequest);
        return ResponseEntity.ok().build();
    }
}

package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.refactor.*;
import com.gelinski.superquiz.model.*;
import com.gelinski.superquiz.repository.*;
import com.gelinski.superquiz.service.strategy.RenderPhaseContext;
import com.gelinski.superquiz.service.strategy.RenderPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhasesService {
    private final ThemeRepository themeRepository;
    private final ColorRepository colorRepository;
    private final RenderPhaseContext renderPhaseContext;

    public RenderPhasesBaseResponse renderPhase(Integer phaseNumber, Integer idTheme, Integer difficultyNumber) {
        Theme theme = themeRepository.findById(idTheme).orElseThrow();
        return renderPhaseContext.getStrategy(phaseNumber).renderPhase(phaseNumber, theme, difficultyNumber);
    }

    public List<ColorDTO> getColors() {
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(color -> new ColorDTO(color.getId(), color.getHex())).toList();
    }

    public void createColor(ColorDTO colorDTO) {
        Color color = new Color();
        color.setHex(colorDTO.getHex());
        colorRepository.save(color);
    }
}

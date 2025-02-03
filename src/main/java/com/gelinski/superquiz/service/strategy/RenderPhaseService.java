package com.gelinski.superquiz.service.strategy;

import com.gelinski.superquiz.dto.refactor.RenderPhasesBaseResponse;
import com.gelinski.superquiz.model.Theme;

public interface RenderPhaseService {
    RenderPhasesBaseResponse renderPhase(Integer phaseNumber, Theme theme, Integer difficultyNumber);
}

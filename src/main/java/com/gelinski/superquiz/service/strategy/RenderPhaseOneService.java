package com.gelinski.superquiz.service.strategy;

import com.gelinski.superquiz.dto.refactor.PhaseOneRenderResponse;
import com.gelinski.superquiz.dto.refactor.PhaseOneWordsDTO;
import com.gelinski.superquiz.dto.refactor.RenderPhasesBaseResponse;
import com.gelinski.superquiz.model.PhaseOneQuestion;
import com.gelinski.superquiz.model.Theme;
import com.gelinski.superquiz.repository.PhaseOneQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RenderPhaseOneService implements RenderPhaseService {
    private final PhaseOneQuestionRepository phaseOneQuestionRepository;

    @Override
    public RenderPhasesBaseResponse renderPhase(Integer phaseNumber, Theme theme, Integer difficultyNumber) {
        PhaseOneRenderResponse phaseOneRenderResponse = new PhaseOneRenderResponse();
        phaseOneRenderResponse.setTotalDifficulties(phaseOneQuestionRepository.countAllByThemeId(theme.getId()));

        List<PhaseOneQuestion> allByThemeId = phaseOneQuestionRepository.findAllByThemeIdWithLimit(theme.getId(), difficultyNumber);

        allByThemeId.forEach(phaseOneQuestion -> {
            phaseOneRenderResponse.getPhaseOneWordsDTOS().add(new PhaseOneWordsDTO(phaseOneQuestion.getId(), phaseOneQuestion.getWord(), phaseOneQuestion.getDescription(), phaseOneQuestion.getColor().getHex()));
            phaseOneRenderResponse.getColors().add(phaseOneQuestion.getColor().getHex());
        });

        return phaseOneRenderResponse;
    }
}

package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.refactor.*;
import com.gelinski.superquiz.model.*;
import com.gelinski.superquiz.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhasesService {
    private final PhaseOneQuestionRepository phaseOneQuestionRepository;
    private final PhaseTwoExerciseRepository phaseTwoExerciseRepository;
    private final ThemeRepository themeRepository;
    private final AnswerRepository answerRepository;
    private final StatementRepository statementRepository;
    private final ColorRepository colorRepository;

    public RenderPhasesBaseResponse renderPhase(Integer phaseNumber, Integer idTheme, Integer difficultyNumber) {
        Theme theme = themeRepository.findById(idTheme).orElseThrow();

        if (phaseNumber == 1) {
            return getPhaseOneRenderResponse(difficultyNumber, theme);
        }

        if (phaseNumber == 2) {
            return getPhaseTwoRenderResponse(theme);
        }

        return null;
    }

    private PhaseOneRenderResponse getPhaseOneRenderResponse(Integer difficultyNumber, Theme theme) {
        PhaseOneRenderResponse phaseOneRenderResponse = new PhaseOneRenderResponse();
        phaseOneRenderResponse.setTotalDifficulties(phaseOneQuestionRepository.countAllByThemeId(theme.getId()));

        List<PhaseOneQuestion> allByThemeId = phaseOneQuestionRepository.findAllByThemeIdWithLimit(theme.getId(), difficultyNumber);

        allByThemeId.forEach(phaseOneQuestion -> {
            phaseOneRenderResponse.setPhaseOneId(phaseOneQuestion.getId());
            phaseOneRenderResponse.getPhaseOneWordsDTOS().add(new PhaseOneWordsDTO(phaseOneQuestion.getWord(), phaseOneQuestion.getDescription(), phaseOneQuestion.getColor().getHex()));
            phaseOneRenderResponse.getColors().add(phaseOneQuestion.getColor().getHex());
        });

        return phaseOneRenderResponse;
    }

    private RenderPhasesBaseResponse getPhaseTwoRenderResponse(Theme theme) {
        PhaseTwoRenderResponse phaseTwoRenderResponse = new PhaseTwoRenderResponse();

        List<PhaseTwoExercise> allByThemeId = phaseTwoExerciseRepository.findAllByThemeId(theme.getId());

        List<PhaseTwoExerciseDTO> phaseTwoExercises = allByThemeId.stream().map(phaseTwoExercise -> {
            PhaseTwoExerciseDTO exercise = new PhaseTwoExerciseDTO();

            List<Statement> statements = statementRepository.findAllByPhaseTwoExerciseId(phaseTwoExercise.getId());
            List<Answer> answers = answerRepository.findAllByPhaseTwoExerciseId(phaseTwoExercise.getId());

            exercise.setPhaseTwoExerciseId(phaseTwoExercise.getId());
            exercise.setStatements(statements.stream().map(Statement::getStatementDescription).toList());
            exercise.setAnswers(answers.stream().map(answer -> new AnswerDTO(answer.getId(), answer.getDescription())).toList());
            exercise.setCorrectAnswerId(answers.stream().filter(Answer::getIsCorrectAnswer).findFirst().orElseThrow().getId());
            return exercise;
        }).toList();

        phaseTwoRenderResponse.setPhaseTwoExerciseDTOS(phaseTwoExercises);

        return phaseTwoRenderResponse;
    }

    public List<ColorDTO> getColors() {
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(color -> new ColorDTO(color.getId(), color.getHex())).toList();
    }
}

package com.gelinski.superquiz.service.strategy;

import com.gelinski.superquiz.dto.refactor.AnswerDTO;
import com.gelinski.superquiz.dto.refactor.PhaseTwoExerciseDTO;
import com.gelinski.superquiz.dto.refactor.PhaseTwoRenderResponse;
import com.gelinski.superquiz.dto.refactor.RenderPhasesBaseResponse;
import com.gelinski.superquiz.model.Answer;
import com.gelinski.superquiz.model.PhaseTwoExercise;
import com.gelinski.superquiz.model.Statement;
import com.gelinski.superquiz.model.Theme;
import com.gelinski.superquiz.repository.AnswerRepository;
import com.gelinski.superquiz.repository.PhaseTwoExerciseRepository;
import com.gelinski.superquiz.repository.StatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RenderPhaseTwoService implements RenderPhaseService {
    private final PhaseTwoExerciseRepository phaseTwoExerciseRepository;
    private final AnswerRepository answerRepository;
    private final StatementRepository statementRepository;

    @Override
    public RenderPhasesBaseResponse renderPhase(Integer phaseNumber, Theme theme, Integer difficultyNumber) {
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
}

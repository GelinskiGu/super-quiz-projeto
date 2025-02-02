package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.dto.refactor.AnsweredQuestionOneDTO;
import com.gelinski.superquiz.dto.refactor.CreatePhaseOneRequest;
import com.gelinski.superquiz.mapper.PhaseOneQuestionMapper;
import com.gelinski.superquiz.mapper.singleton.PhaseOneQuestionMapperSingleton;
import com.gelinski.superquiz.model.*;
import com.gelinski.superquiz.repository.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhaseOneService {
    private final PhaseOneAnswerRepository phaseOneAnswerRepository;
    private final StudentRepository studentRepository;

    private final ThemeRepository themeRepository;
    private final ColorRepository colorRepository;
    private final PhaseOneQuestionRepository phaseOneQuestionRepository;

    private final Gson gson = new Gson();

    public Boolean savePhaseOneAnswer(AnsweredQuestionOneDTO request, Long studentId) {
        log.info("AnsweredQuestionDTO: {}, studentId {}", gson.toJson(request), studentId);
        try {
            Student student = studentRepository.findById(studentId).orElseThrow();
            PhaseOneQuestion phaseOneQuestion = phaseOneQuestionRepository.findById(request.getPhaseOneId()).orElseThrow();

            PhaseOneAnswer phaseOneAnswer = new PhaseOneAnswer();
            phaseOneAnswer.setStudent(student);
            phaseOneAnswer.setPhaseOneQuestion(phaseOneQuestion);
            phaseOneAnswer.setIdColor(request.getIdColor());
            phaseOneAnswer.setSeconds(request.getSeconds());
            phaseOneAnswer.setCorrect(Objects.equals(request.getIdColor(), phaseOneQuestion.getColor().getId()));
            phaseOneAnswerRepository.save(phaseOneAnswer);
            return phaseOneAnswer.isCorrect();
        } catch (Exception e) {
            log.error("Error saving student question: {}", e.getMessage());
            throw new RuntimeException("Error saving student question");
        }
    }


    public void createExercise(CreatePhaseOneRequest request) {
        Theme theme = themeRepository.findById(request.getIdTheme()).orElseThrow();
        Color color = colorRepository.findById(request.getIdColor()).orElseThrow();

        PhaseOneQuestion mappedObject = PhaseOneQuestionMapperSingleton.getInstance().toEntity(request, theme, color);
        phaseOneQuestionRepository.save(mappedObject);
    }
}

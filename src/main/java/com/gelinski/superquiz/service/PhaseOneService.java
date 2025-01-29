package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.model.PhaseOneAnswer;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.repository.PhaseOneAnswerRepository;
import com.gelinski.superquiz.repository.StudentRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhaseOneService {
    private final PhaseOneAnswerRepository phaseOneAnswerRepository;
    private final StudentRepository studentRepository;
    private final Gson gson = new Gson();

    public Boolean savePhaseOneAnswer(AnsweredQuestionDTO answeredQuestionDTO, Long studentId) {
        log.info("AnsweredQuestionDTO: {}, studentId {}", gson.toJson(answeredQuestionDTO), studentId);
        try {
            Student student = studentRepository.findById(studentId).orElseThrow();
            PhaseOneAnswer phaseOneAnswer = new PhaseOneAnswer();
            phaseOneAnswer.setStudent(student);
            phaseOneAnswer.setAnswer(answeredQuestionDTO.getAnswer());
            phaseOneAnswer.setSeconds(answeredQuestionDTO.getSeconds());
            phaseOneAnswer.setCorrectAnswer(answeredQuestionDTO.getCorrectAnswer());
            phaseOneAnswer.setCorrect(Objects.equals(answeredQuestionDTO.getAnswer(), answeredQuestionDTO.getCorrectAnswer()));
            phaseOneAnswerRepository.save(phaseOneAnswer);
            return phaseOneAnswer.isCorrect();
        } catch (Exception e) {
            log.error("Error saving student question: {}", e.getMessage());
            throw new RuntimeException("Error saving student question");
        }
    }
}

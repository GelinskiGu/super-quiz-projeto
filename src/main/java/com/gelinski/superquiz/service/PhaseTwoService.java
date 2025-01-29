package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.model.Answer;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.model.StudentQuestion;
import com.gelinski.superquiz.repository.QuestionRepository;
import com.gelinski.superquiz.repository.StudentQuestionRepository;
import com.gelinski.superquiz.repository.StudentRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhaseTwoService {
    private final QuestionRepository questionRepository;
    private final StudentQuestionRepository studentQuestionRepository;
    private final StudentRepository studentRepository;
    private final Gson gson = new Gson();

    public List<Answer> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Boolean saveStudentQuestion(AnsweredQuestionDTO answeredQuestionDTO, Long studentId, Long questionId) {
        log.info("AnsweredQuestionDTO: {}, studentId {}, questionId {}", gson.toJson(answeredQuestionDTO), studentId, questionId);
        try {
            Answer answer = questionRepository.findById(questionId).orElseThrow();
            Student student = studentRepository.findById(studentId).orElseThrow();
            StudentQuestion studentQuestion = new StudentQuestion();

            studentQuestion.setCorrect(Objects.equals(answeredQuestionDTO.getAnswer(), answer.getAnswer()));
            studentQuestion.setAnswer(answeredQuestionDTO.getAnswer());
            studentQuestion.setCorrectAnswer(answer.getAnswer());
            studentQuestion.setSeconds(answeredQuestionDTO.getSeconds());
            studentQuestion.setStudent(student);
            studentQuestion.setAnswer(answer);
            studentQuestionRepository.save(studentQuestion);
            return studentQuestion.isCorrect();
        } catch (Exception e) {
            log.error("Error saving student question: {}", e.getMessage());
            throw new RuntimeException("Error saving student question");
        }
    }
}

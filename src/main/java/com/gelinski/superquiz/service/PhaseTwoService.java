package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.AnsweredQuestionDTO;
import com.gelinski.superquiz.dto.refactor.CreatePhaseTwoRequest;
import com.gelinski.superquiz.model.*;
import com.gelinski.superquiz.repository.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhaseTwoService {
    private final QuestionRepository questionRepository;
    private final StudentQuestionRepository studentQuestionRepository;
    private final StudentRepository studentRepository;

    private final ThemeRepository themeRepository;
    private final PhaseTwoExerciseRepository phaseTwoExerciseRepository;
    private final PhaseOneQuestionRepository phaseOneQuestionRepository;
    private final StatementRepository statementRepository;
    private final AnswerRepository answerRepository;

    private final Gson gson = new Gson();

    public List<Answer> findAllQuestions() {
        return questionRepository.findAll();
    }

    public void createExercise(CreatePhaseTwoRequest request) {
        PhaseTwoExercise phaseTwoEntity = savePhaseTwoExercise(request);
        saveStatements(request, phaseTwoEntity);
        saveAnswers(request, phaseTwoEntity);
    }

    private void saveAnswers(CreatePhaseTwoRequest request, PhaseTwoExercise phaseTwoEntity) {
        List<Answer> answers = request.getAnswers().stream().map(answerRequest -> {
            Answer answer = new Answer();
            answer.setPhaseTwoExercise(phaseTwoEntity);
            answer.setDescription(answerRequest);
            answer.setIsCorrectAnswer(Objects.equals(answerRequest, request.getCorrectAnswer()));
            return answer;
        }).toList();
        answerRepository.saveAll(answers);
    }

    private void saveStatements(CreatePhaseTwoRequest request, PhaseTwoExercise phaseTwoEntity) {
        List<Statement> statements = request.getStatements().stream().map(statementRequest -> {
            Statement statement = new Statement();
            statement.setPhaseTwoExercise(phaseTwoEntity);
            statement.setStatementDescription(statementRequest);
            return statement;
        }).toList();
        statementRepository.saveAll(statements);
    }

    private PhaseTwoExercise savePhaseTwoExercise(CreatePhaseTwoRequest request) {
        PhaseTwoExercise phaseTwoExercise = new PhaseTwoExercise();
        Theme theme = themeRepository.findById(request.getIdTheme()).orElseThrow();

        phaseTwoExercise.setWord(request.getWord());
        phaseTwoExercise.setTheme(theme);
        return phaseTwoExerciseRepository.save(phaseTwoExercise);
    }

    public List<String> getWords(Integer themeId) {
        List<PhaseOneQuestion> allByThemeId = phaseOneQuestionRepository.findAllByThemeId(themeId);
        return allByThemeId.stream().map(PhaseOneQuestion::getWord).toList();
    }

    public Boolean saveStudentQuestion(AnsweredQuestionDTO answeredQuestionDTO, Long studentId) {
        log.info("AnsweredQuestionDTO: {}, studentId {}", gson.toJson(answeredQuestionDTO), studentId);
        try {
            Answer answer = questionRepository.findById(answeredQuestionDTO.getAnswerId()).orElseThrow();
            Student student = studentRepository.findById(studentId).orElseThrow();
            StudentQuestion studentQuestion = new StudentQuestion();

            studentQuestion.setCorrect(answer.getIsCorrectAnswer());
            studentQuestion.setAnswer(answer);
            studentQuestion.setSeconds(answeredQuestionDTO.getSeconds());
            studentQuestion.setStudent(student);
            studentQuestion.setWord(answer.getPhaseTwoExercise().getWord());
            studentQuestionRepository.save(studentQuestion);
            return studentQuestion.isCorrect();
        } catch (Exception e) {
            log.error("Error saving student question: {}", e.getMessage());
            throw new RuntimeException("Error saving student question");
        }
    }
}

package com.gelinski.superquiz.builder.report;

import com.gelinski.superquiz.dto.*;
import com.gelinski.superquiz.model.PhaseOneAnswer;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.model.StudentQuestion;
import com.gelinski.superquiz.repository.PhaseOneAnswerRepository;
import com.gelinski.superquiz.repository.StudentQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportByStudentBuilder implements Builder {
    private String studentName;
    private PhaseOneReportDTO phaseOneReportDTO;
    private PhaseTwoReportDTO phaseTwoReportDTO;

    private final PhaseOneAnswerRepository phaseOneAnswerRepository;
    private final StudentQuestionRepository studentQuestionRepository;

    @Override
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public void setPhaseOneReportDTO(Student student) {
        List<PhaseOneAnswer> phaseOneAnswers = phaseOneAnswerRepository.findByStudent(student);
        Map<String, List<PhaseOneAnswer>> phaseOneAnswerElementMap = phaseOneAnswers.stream()
                .collect(Collectors.groupingBy(phaseOneAnswer -> phaseOneAnswer.getPhaseOneQuestion().getWord()));

        List<ElementsPhaseOneDTO> elementsPhaseOneDTOS = new ArrayList<>();

        phaseOneAnswerElementMap.forEach((key, value) -> {
            ElementsPhaseOneDTO elementsPhaseOneDTO = new ElementsPhaseOneDTO();
            elementsPhaseOneDTO.setElement(key);
            elementsPhaseOneDTO.setTries(Long.valueOf(value.stream().filter(phase -> !phase.isCorrect()).count()).intValue());
            elementsPhaseOneDTO.setTime(convertTime(value.stream().max(Comparator.comparing(PhaseOneAnswer::getSeconds)).get().getSeconds()));
            elementsPhaseOneDTOS.add(elementsPhaseOneDTO);
        });

        PhaseOneReportDTO builtPhaseOneReportDTO = new PhaseOneReportDTO();
        builtPhaseOneReportDTO.setElements(elementsPhaseOneDTOS);

        this.phaseOneReportDTO = builtPhaseOneReportDTO;
    }

    @Override
    public void setPhaseTwoReportDTO(Student student) {
        List<StudentQuestion> studentQuestions = studentQuestionRepository.findByStudent(student);
        Map<String, List<StudentQuestion>> studentQuestionElementMap = studentQuestions.stream()
                .collect(Collectors.groupingBy(studentQuestion -> studentQuestion.getAnswer().getPhaseTwoExercise().getWord()));

        List<PartsPhaseTwoDTO> partsPhaseTwoDTOS = new ArrayList<>();

        studentQuestionElementMap.forEach((key, value) -> {
            PartsPhaseTwoDTO partsPhaseTwoDTO = new PartsPhaseTwoDTO();
            partsPhaseTwoDTO.setPart(key);
            partsPhaseTwoDTO.setTries(value.size());
            partsPhaseTwoDTO.setTime(convertTime((long) value.stream().mapToLong(StudentQuestion::getSeconds).average().orElse(0)));
            partsPhaseTwoDTOS.add(partsPhaseTwoDTO);
        });

        PhaseTwoReportDTO builtPhaseTwoReport = new PhaseTwoReportDTO();
        builtPhaseTwoReport.setParts(partsPhaseTwoDTOS);

        this.phaseTwoReportDTO = builtPhaseTwoReport;
    }

    public ReportDTO getResult() {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setStudentName(studentName);
        reportDTO.setPhaseOne(phaseOneReportDTO);
        reportDTO.setPhaseTwo(phaseTwoReportDTO);
        return reportDTO;
    }

    private String convertTime(Long seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }
}

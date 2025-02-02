package com.gelinski.superquiz.service;

import com.gelinski.superquiz.dto.*;
import com.gelinski.superquiz.model.PhaseOneAnswer;
import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.model.StudentQuestion;
import com.gelinski.superquiz.repository.PhaseOneAnswerRepository;
import com.gelinski.superquiz.repository.StudentQuestionRepository;
import com.gelinski.superquiz.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final StudentQuestionRepository studentQuestionRepository;
    private final StudentRepository studentRepository;
    private final PhaseOneAnswerRepository phaseOneAnswerRepository;

    public List<ReportDTO> generateAllReport() {
        List<Student> students = studentRepository.findAll();
        List<ReportDTO> reportDTOS = new ArrayList<>();
        students.forEach(student -> reportDTOS.add(generateReportByStudent(student.getIdStudent())));
        List<ReportDTO> reportsWillBeDeleted = reportDTOS.stream().filter(reportDTO -> reportDTO.getPhaseOne().getElements().isEmpty() && reportDTO.getPhaseTwo().getParts().isEmpty()).toList();

        reportsWillBeDeleted.forEach(reportDTOS::remove);
        return reportDTOS;
    }

    public List<ReportDTO> generateReportByGroup(Long idGroup) {
        List<Student> students = studentRepository.findByGroup(idGroup);
        if (students.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportDTO> reportDTOS = new ArrayList<>();
        students.forEach(student -> reportDTOS.add(generateReportByStudent(student.getIdStudent())));
        return reportDTOS;
    }

    public ReportDTO generateReportByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        PhaseOneReportDTO phaseOneReportDTO = getPhaseOneReport(student);
        PhaseTwoReportDTO phaseTwoReportDTO = getPhaseTwoReport(student);
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setStudentName(student.getName());
        reportDTO.setPhaseOne(phaseOneReportDTO);
        reportDTO.setPhaseTwo(phaseTwoReportDTO);
        return reportDTO;
    }

    private PhaseOneReportDTO getPhaseOneReport(Student student) {
        List<PhaseOneAnswer> phaseOneAnswers = phaseOneAnswerRepository.findByStudent(student);
        Map<String, List<PhaseOneAnswer>> phaseOneAnswerElementMap = phaseOneAnswers.stream()
                .collect(Collectors.groupingBy(phaseOneAnswer -> phaseOneAnswer.getPhaseOneQuestion().getWord()));

        List<ElementsPhaseOneDTO> elementsPhaseOneDTOS = new ArrayList<>();

        phaseOneAnswerElementMap.forEach((key, value) -> {
            ElementsPhaseOneDTO elementsPhaseOneDTO = new ElementsPhaseOneDTO();
            elementsPhaseOneDTO.setElement(key);
            elementsPhaseOneDTO.setTries(value.size());
            elementsPhaseOneDTO.setTime(convertTime(value.stream().max(Comparator.comparing(PhaseOneAnswer::getSeconds)).get().getSeconds()));
            elementsPhaseOneDTOS.add(elementsPhaseOneDTO);
        });

        PhaseOneReportDTO phaseOneReportDTO = new PhaseOneReportDTO();
        phaseOneReportDTO.setElements(elementsPhaseOneDTOS);
        return phaseOneReportDTO;
    }

    private PhaseTwoReportDTO getPhaseTwoReport(Student student) {
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

        PhaseTwoReportDTO phaseTwoReportDTO = new PhaseTwoReportDTO();
        phaseTwoReportDTO.setParts(partsPhaseTwoDTOS);

        return phaseTwoReportDTO;
    }

    private String convertTime(Long seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    private String convertTries(List<StudentQuestion> studentQuestions) {
        return String.format("%s/%s", studentQuestions.stream().filter(StudentQuestion::isCorrect).count(), studentQuestions.size());
    }
}

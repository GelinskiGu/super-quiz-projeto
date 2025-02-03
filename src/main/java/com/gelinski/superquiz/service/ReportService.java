package com.gelinski.superquiz.service;

import com.gelinski.superquiz.builder.report.Director;
import com.gelinski.superquiz.builder.report.ReportByStudentBuilder;
import com.gelinski.superquiz.dto.*;
import com.gelinski.superquiz.iterator.student.StudentIterator;
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
    private final StudentRepository studentRepository;
    private final ReportByStudentBuilder reportByStudentBuilder;

    public List<ReportDTO> generateAllReport() {
        List<Student> students = studentRepository.findAll();
        List<ReportDTO> reportDTOS = new ArrayList<>();

        StudentIterator studentIterator = new StudentIterator(students);
        while (studentIterator.hasNext()) {
            Student student = studentIterator.next();
            reportDTOS.add(generateReportByStudent(student.getIdStudent()));
        }

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

        StudentIterator studentIterator = new StudentIterator(students);
        while (studentIterator.hasNext()) {
            Student student = studentIterator.next();
            reportDTOS.add(generateReportByStudent(student.getIdStudent()));
        }

        return reportDTOS;
    }

    public ReportDTO generateReportByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        Director director = new Director();
        director.generateReportByStudent(reportByStudentBuilder, student);

        return reportByStudentBuilder.getResult();
    }
}

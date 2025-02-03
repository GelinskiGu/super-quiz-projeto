package com.gelinski.superquiz.builder.report;

import com.gelinski.superquiz.model.Student;
import org.springframework.stereotype.Component;

@Component
public class Director {
    public void generateReportByStudent(Builder builder, Student student) {
        builder.setStudentName(student.getName());
        builder.setPhaseOneReportDTO(student);
        builder.setPhaseTwoReportDTO(student);
    }
}

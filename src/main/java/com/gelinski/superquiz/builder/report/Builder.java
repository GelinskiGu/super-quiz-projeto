package com.gelinski.superquiz.builder.report;

import com.gelinski.superquiz.model.Student;

public interface Builder {
    void setStudentName(String studentName);
    void setPhaseOneReportDTO(Student student);
    void setPhaseTwoReportDTO(Student student);
}

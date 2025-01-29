package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Student;
import com.gelinski.superquiz.model.StudentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, Long> {
    List<StudentQuestion> findByStudent(Student student);
}
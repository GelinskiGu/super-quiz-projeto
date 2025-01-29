package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.PhaseOneAnswer;
import com.gelinski.superquiz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhaseOneAnswerRepository extends JpaRepository<PhaseOneAnswer, Long> {
    List<PhaseOneAnswer> findByStudent(Student student);
}
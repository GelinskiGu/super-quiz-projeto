package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByPhaseTwoExerciseId(Long phaseTwoExerciseId);
}
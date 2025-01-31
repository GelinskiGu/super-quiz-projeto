package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
    List<Statement> findAllByPhaseTwoExerciseId(Long phaseTwoExerciseId);
}
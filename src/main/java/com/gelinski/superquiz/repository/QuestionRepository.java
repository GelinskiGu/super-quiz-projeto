package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Answer, Long> {
}
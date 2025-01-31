package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.PhaseOneQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhaseOneQuestionRepository extends JpaRepository<PhaseOneQuestion, Long> {
    List<PhaseOneQuestion> findAllByThemeId(Integer themeId);

    @Query(value = "SELECT * FROM tb_phase_one_question WHERE id_theme = ?1 order by random() LIMIT ?2", nativeQuery = true)
    List<PhaseOneQuestion> findAllByThemeIdWithLimit(Integer themeId, Integer limit);

    Integer countAllByThemeId(Integer themeId);
}
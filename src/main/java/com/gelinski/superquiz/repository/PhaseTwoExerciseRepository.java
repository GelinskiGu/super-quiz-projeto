package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.PhaseTwoExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhaseTwoExerciseRepository extends JpaRepository<PhaseTwoExercise, Long> {
    List<PhaseTwoExercise> findAllByThemeId(Integer themeId);
}
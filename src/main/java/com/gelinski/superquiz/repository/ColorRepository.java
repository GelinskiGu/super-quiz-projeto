package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
}
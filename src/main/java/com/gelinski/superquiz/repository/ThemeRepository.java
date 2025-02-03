package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    @Query("SELECT t FROM Theme t INNER JOIN t.group g WHERE g.idGroup = :groupId")
    List<Theme> findAllByGroupId(Long groupId);

    @Query("SELECT t FROM Theme t INNER JOIN t.group g WHERE g.teacher.idUser = :teacherId")
    List<Theme> findAllByTeacherId(Long teacherId);
}
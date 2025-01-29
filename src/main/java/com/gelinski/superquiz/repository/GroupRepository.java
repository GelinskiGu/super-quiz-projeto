package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_group WHERE id_user = ?1")
    List<Group> findByTeacher(Long teacherId);
}
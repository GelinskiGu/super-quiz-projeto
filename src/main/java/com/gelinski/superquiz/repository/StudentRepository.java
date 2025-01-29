package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true, value = "select * from tb_student inner join tb_group " +
            "on tb_student.id_group = tb_group.id_group where tb_group.id_user = ?1")
    List<Student> findByTeacher(Long teacherId);

    @Query(nativeQuery = true, value = "select * from tb_student where id_group = ?1")
    List<Student> findByGroup(Long groupId);
}
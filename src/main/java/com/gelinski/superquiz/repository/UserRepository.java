package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from tb_user u where u.username = ?1", nativeQuery = true)
    User findByUsername(String username);
}

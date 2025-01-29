package com.gelinski.superquiz.repository;

import com.gelinski.superquiz.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByDescription(String description);
}
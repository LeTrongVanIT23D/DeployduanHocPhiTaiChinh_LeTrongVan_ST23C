package com.example.demo.students.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.students.model.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}


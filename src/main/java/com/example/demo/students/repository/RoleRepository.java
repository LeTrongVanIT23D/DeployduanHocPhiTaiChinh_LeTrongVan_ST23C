package com.example.demo.students.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.students.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}


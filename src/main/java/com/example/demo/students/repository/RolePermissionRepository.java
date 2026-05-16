package com.example.demo.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.students.model.entity.RolePermission;

import java.util.UUID;
import java.util.List;

public interface RolePermissionRepository 
        extends JpaRepository<RolePermission, UUID> {

    List<RolePermission> findByRoleId(UUID roleId);

    void deleteByRoleIdAndPermissionId(UUID roleId, UUID permissionId);
}

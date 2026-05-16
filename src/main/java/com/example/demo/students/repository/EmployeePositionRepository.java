package com.example.demo.students.repository;

import com.example.demo.students.model.entity.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, UUID> {
    List<EmployeePosition> findByEmployeeId(UUID employeeId);
}

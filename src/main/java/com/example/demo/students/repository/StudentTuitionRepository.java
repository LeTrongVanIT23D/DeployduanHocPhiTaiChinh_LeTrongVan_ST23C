package com.example.demo.students.repository;

import com.example.demo.students.model.entity.StudentTuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentTuitionRepository extends JpaRepository<StudentTuition, UUID> {
    List<StudentTuition> findByStudent_CodeContainingIgnoreCase(String studentCode);
    List<StudentTuition> findByStudent_FullNameContainingIgnoreCase(String studentName);
}

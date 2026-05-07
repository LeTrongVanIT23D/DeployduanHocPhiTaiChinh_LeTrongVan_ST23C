package com.example.demo.students.repository;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.students.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByNameContainingIgnoreCase(String name);
    
    Optional<Student> findByStudentId(String studentId);
    
    @Transactional
    void deleteByStudentId(String studentId);
}


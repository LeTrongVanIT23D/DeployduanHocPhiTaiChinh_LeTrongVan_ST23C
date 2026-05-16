package com.example.demo.students.repository;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.students.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByFullNameContainingIgnoreCase(String name);
    
    Optional<Student> findByCode(String code);
    
    @Transactional
    void deleteByCode(String code);
}


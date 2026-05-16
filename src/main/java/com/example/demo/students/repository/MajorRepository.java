package com.example.demo.students.repository;

import com.example.demo.students.model.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MajorRepository extends JpaRepository<Major, UUID> {
    Optional<Major> findByCode(String code);
    List<Major> findByNameContainingIgnoreCase(String name);
}

package com.example.demo.students.repository;

import com.example.demo.students.model.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, UUID> {
    Optional<TrainingProgram> findByCode(String code);
    List<TrainingProgram> findByNameContainingIgnoreCase(String name);
}

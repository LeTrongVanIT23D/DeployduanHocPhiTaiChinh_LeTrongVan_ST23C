package com.example.demo.students.repository;
import com.example.demo.students.model.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {}

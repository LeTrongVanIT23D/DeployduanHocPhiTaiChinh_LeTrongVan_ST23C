package com.example.demo.students.repository;
import com.example.demo.students.model.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYear, UUID> {}

package com.example.demo.students.repository;

import com.example.demo.students.model.entity.TuitionFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TuitionFeeRepository extends JpaRepository<TuitionFee, UUID> {
}

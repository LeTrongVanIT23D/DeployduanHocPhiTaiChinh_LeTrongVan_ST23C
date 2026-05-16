package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "equivalent_courses")
public class EquivalentCourse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_course_id")
    private Course originalCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equivalent_course_id")
    private Course equivalentCourse;

    @Column(name = "equivalence_type")
    private Integer equivalenceType; // 1: Thay thế hoàn toàn; 2: Tương đương song song

    @Column(name = "effect_date")
    private LocalDate effectDate;

    @Column(name = "note", length = 500)
    private String note;

    public EquivalentCourse() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Course getOriginalCourse() { return originalCourse; }
    public void setOriginalCourse(Course originalCourse) { this.originalCourse = originalCourse; }

    public Course getEquivalentCourse() { return equivalentCourse; }
    public void setEquivalentCourse(Course equivalentCourse) { this.equivalentCourse = equivalentCourse; }

    public Integer getEquivalenceType() { return equivalenceType; }
    public void setEquivalenceType(Integer equivalenceType) { this.equivalenceType = equivalenceType; }

    public LocalDate getEffectDate() { return effectDate; }
    public void setEffectDate(LocalDate effectDate) { this.effectDate = effectDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

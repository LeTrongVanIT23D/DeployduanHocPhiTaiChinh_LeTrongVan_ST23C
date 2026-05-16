package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "graduation_conditions")
public class GraduationCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_program_id")
    private TrainingProgram trainingProgram;

    @Column(name = "condition_type", length = 100)
    private String conditionType; // Tín chỉ, Chứng chỉ ngoại ngữ, Chứng chỉ tin học

    @Column(name = "required_value", length = 255)
    private String requiredValue; // Số tín chỉ tối thiểu, điểm TOEIC tối thiểu...

    @Column(name = "description", length = 255)
    private String description;

    public GraduationCondition() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public TrainingProgram getTrainingProgram() { return trainingProgram; }
    public void setTrainingProgram(TrainingProgram trainingProgram) { this.trainingProgram = trainingProgram; }

    public String getConditionType() { return conditionType; }
    public void setConditionType(String conditionType) { this.conditionType = conditionType; }

    public String getRequiredValue() { return requiredValue; }
    public void setRequiredValue(String requiredValue) { this.requiredValue = requiredValue; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

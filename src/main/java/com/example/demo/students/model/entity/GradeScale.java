package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "grade_scales")
public class GradeScale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "scale_type", length = 50)
    private String scaleType; // Hệ 10, Hệ 4, Chữ

    @Column(name = "score_10", precision = 5, scale = 2)
    private BigDecimal score10;

    @Column(name = "score_4", precision = 5, scale = 2)
    private BigDecimal score4;

    @Column(name = "letter_grade", length = 5)
    private String letterGrade;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_pass")
    private Boolean isPass;

    @Column(name = "classification", length = 100)
    private String classification; // Giỏi, Khá, TB...

    @Column(name = "status", length = 50)
    private String status;

    public GradeScale() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getScaleType() { return scaleType; }
    public void setScaleType(String scaleType) { this.scaleType = scaleType; }

    public BigDecimal getScore10() { return score10; }
    public void setScore10(BigDecimal score10) { this.score10 = score10; }

    public BigDecimal getScore4() { return score4; }
    public void setScore4(BigDecimal score4) { this.score4 = score4; }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsPass() { return isPass; }
    public void setIsPass(Boolean isPass) { this.isPass = isPass; }

    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "credits", precision = 5, scale = 1)
    private BigDecimal credits;

    @Column(name = "cource_type", length = 20)
    private String courseType;

    @Column(name = "theory_hours", precision = 5, scale = 1)
    private BigDecimal theoryHours;

    @Column(name = "practice_hours", precision = 5, scale = 1)
    private BigDecimal practiceHours;

    @Column(name = "self_study_hours", precision = 5, scale = 1)
    private BigDecimal selfStudyHours;

    @Column(name = "internship_credits", precision = 5, scale = 1)
    private BigDecimal internshipCredits;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    public Course() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public BigDecimal getCredits() { return credits; }
    public void setCredits(BigDecimal credits) { this.credits = credits; }

    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }

    public BigDecimal getTheoryHours() { return theoryHours; }
    public void setTheoryHours(BigDecimal theoryHours) { this.theoryHours = theoryHours; }

    public BigDecimal getPracticeHours() { return practiceHours; }
    public void setPracticeHours(BigDecimal practiceHours) { this.practiceHours = practiceHours; }

    public BigDecimal getSelfStudyHours() { return selfStudyHours; }
    public void setSelfStudyHours(BigDecimal selfStudyHours) { this.selfStudyHours = selfStudyHours; }

    public BigDecimal getInternshipCredits() { return internshipCredits; }
    public void setInternshipCredits(BigDecimal internshipCredits) { this.internshipCredits = internshipCredits; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

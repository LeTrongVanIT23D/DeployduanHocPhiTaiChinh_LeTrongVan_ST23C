package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "training_programs")
public class TrainingProgram extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "degree_level", length = 50)
    private String degreeLevel; // Đại học, Cao học, Tiến sĩ

    @Column(name = "education_type", length = 50)
    private String educationType; // Chính quy, Liên thông, VB2

    @Column(name = "total_credits", precision = 5, scale = 1)
    private BigDecimal totalCredits;

    @Column(name = "required_credits", precision = 5, scale = 1)
    private BigDecimal requiredCredits;

    @Column(name = "elective_credits", precision = 5, scale = 1)
    private BigDecimal electiveCredits;

    @Column(name = "internship_credits", precision = 5, scale = 1)
    private BigDecimal internshipCredits;

    @Column(name = "thesis_credits", precision = 5, scale = 1)
    private BigDecimal thesisCredits;

    @Column(name = "admission_year")
    private LocalDate admissionYear;

    @Column(name = "duration_years", precision = 5, scale = 1)
    private BigDecimal durationYears;

    @Column(name = "max_duration_years", precision = 5, scale = 1)
    private BigDecimal maxDurationYears;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "objectives", columnDefinition = "NVARCHAR(MAX)")
    private String objectives;

    @Column(name = "learning_outcomes", columnDefinition = "NVARCHAR(MAX)")
    private String learningOutcomes;

    @Column(name = "version", length = 20)
    private String version;

    @Column(name = "status", length = 20)
    private String status;

    public TrainingProgram() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public Major getMajor() { return major; }
    public void setMajor(Major major) { this.major = major; }

    public AcademicYear getAcademicYear() { return academicYear; }
    public void setAcademicYear(AcademicYear academicYear) { this.academicYear = academicYear; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getDegreeLevel() { return degreeLevel; }
    public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }

    public String getEducationType() { return educationType; }
    public void setEducationType(String educationType) { this.educationType = educationType; }

    public BigDecimal getTotalCredits() { return totalCredits; }
    public void setTotalCredits(BigDecimal totalCredits) { this.totalCredits = totalCredits; }

    public BigDecimal getRequiredCredits() { return requiredCredits; }
    public void setRequiredCredits(BigDecimal requiredCredits) { this.requiredCredits = requiredCredits; }

    public BigDecimal getElectiveCredits() { return electiveCredits; }
    public void setElectiveCredits(BigDecimal electiveCredits) { this.electiveCredits = electiveCredits; }

    public BigDecimal getInternshipCredits() { return internshipCredits; }
    public void setInternshipCredits(BigDecimal internshipCredits) { this.internshipCredits = internshipCredits; }

    public BigDecimal getThesisCredits() { return thesisCredits; }
    public void setThesisCredits(BigDecimal thesisCredits) { this.thesisCredits = thesisCredits; }

    public LocalDate getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(LocalDate admissionYear) { this.admissionYear = admissionYear; }

    public BigDecimal getDurationYears() { return durationYears; }
    public void setDurationYears(BigDecimal durationYears) { this.durationYears = durationYears; }

    public BigDecimal getMaxDurationYears() { return maxDurationYears; }
    public void setMaxDurationYears(BigDecimal maxDurationYears) { this.maxDurationYears = maxDurationYears; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getObjectives() { return objectives; }
    public void setObjectives(String objectives) { this.objectives = objectives; }

    public String getLearningOutcomes() { return learningOutcomes; }
    public void setLearningOutcomes(String learningOutcomes) { this.learningOutcomes = learningOutcomes; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student_tuition")
public class StudentTuition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @Column(name = "semester_id")
    private UUID semesterId;
    
    @ManyToOne
    @JoinColumn(name = "tuition_fee_id")
    private TuitionFee tuitionFee;
    
    @Column(name = "total_credits")
    private Integer totalCredits;
    
    @Column(name = "raw_amount", precision = 15, scale = 2)
    private BigDecimal rawAmount;
    
    @Column(name = "scholarship_deduction", precision = 15, scale = 2)
    private BigDecimal scholarshipDeduction;
    
    @Column(name = "exemption_amount", precision = 15, scale = 2)
    private BigDecimal exemptionAmount;
    
    @Column(name = "net_amount", precision = 15, scale = 2)
    private BigDecimal netAmount;
    
    @Column(name = "paid_amount", precision = 15, scale = 2)
    private BigDecimal paidAmount;
    
    @Column(name = "debt_amount", precision = 15, scale = 2)
    private BigDecimal debtAmount;
    
    @Column(name = "status")
    private Short status; // 1-PAID, 2-PARTIAL, 3-DEBT, 4-OVERDUE
    
    @Column(name = "deadline")
    private LocalDate deadline;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "created_by")
    private UUID createdBy;
    
    @Column(name = "updated_by")
    private UUID updatedBy;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @Column(name = "deleted_by")
    private UUID deletedBy;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @JsonProperty("studentId")
    public String getStudentId() {
        return student != null ? student.getStudentId() : null;
    }

    public UUID getSemesterId() { return semesterId; }
    public void setSemesterId(UUID semesterId) { this.semesterId = semesterId; }
    public TuitionFee getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(TuitionFee tuitionFee) { this.tuitionFee = tuitionFee; }
    public Integer getTotalCredits() { return totalCredits; }
    public void setTotalCredits(Integer totalCredits) { this.totalCredits = totalCredits; }
    public BigDecimal getRawAmount() { return rawAmount; }
    public void setRawAmount(BigDecimal rawAmount) { this.rawAmount = rawAmount; }
    public BigDecimal getScholarshipDeduction() { return scholarshipDeduction; }
    public void setScholarshipDeduction(BigDecimal scholarshipDeduction) { this.scholarshipDeduction = scholarshipDeduction; }
    public BigDecimal getExemptionAmount() { return exemptionAmount; }
    public void setExemptionAmount(BigDecimal exemptionAmount) { this.exemptionAmount = exemptionAmount; }
    public BigDecimal getNetAmount() { return netAmount; }
    public void setNetAmount(BigDecimal netAmount) { this.netAmount = netAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
    public BigDecimal getDebtAmount() { return debtAmount; }
    public void setDebtAmount(BigDecimal debtAmount) { this.debtAmount = debtAmount; }
    public Short getStatus() { return status; }
    public void setStatus(Short status) { this.status = status; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
    public UUID getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(UUID updatedBy) { this.updatedBy = updatedBy; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    public UUID getDeletedBy() { return deletedBy; }
    public void setDeletedBy(UUID deletedBy) { this.deletedBy = deletedBy; }
}

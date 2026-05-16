package com.example.demo.students.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "course_registrations")
public class CourseRegistration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_section_id") // Using course_section_id instead of course_class_id as per Group V
    private CourseSection courseSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_period_id")
    private RegistrationPeriod registrationPeriod;

    @Column(name = "registration_type")
    private Integer registrationType; // 1: Học mới; 2: Học lại; 3: Cải thiện

    // Using UUID for replaced grade ID since we don't have the grade entity yet or we want to keep it decoupled
    @Column(name = "replaced_grade_id")
    private UUID replacedGradeId;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "status")
    private Integer status; // 1: Thành công; 2: Chờ thanh toán; 3: Đã hủy

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Version
    @Column(name = "row_version")
    private Long rowVersion;

    public CourseRegistration() {}

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public CourseSection getCourseSection() { return courseSection; }
    public void setCourseSection(CourseSection courseSection) { this.courseSection = courseSection; }

    public RegistrationPeriod getRegistrationPeriod() { return registrationPeriod; }
    public void setRegistrationPeriod(RegistrationPeriod registrationPeriod) { this.registrationPeriod = registrationPeriod; }

    public Integer getRegistrationType() { return registrationType; }
    public void setRegistrationType(Integer registrationType) { this.registrationType = registrationType; }

    public UUID getReplacedGradeId() { return replacedGradeId; }
    public void setReplacedGradeId(UUID replacedGradeId) { this.replacedGradeId = replacedGradeId; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Boolean getIsPaid() { return isPaid; }
    public void setIsPaid(Boolean isPaid) { this.isPaid = isPaid; }

    public Long getRowVersion() { return rowVersion; }
    public void setRowVersion(Long rowVersion) { this.rowVersion = rowVersion; }
}

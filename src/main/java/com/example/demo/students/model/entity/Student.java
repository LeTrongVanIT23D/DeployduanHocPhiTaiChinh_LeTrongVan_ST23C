package com.example.demo.students.model.entity;

import jakarta.persistence.*;

import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "date_of_birth")
    private java.time.LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender; // 1: Nam, 2: Nữ, 0: Khác

    @Column(name = "personal_identification_number", length = 20)
    private String personalIdentificationNumber;

    @Column(name = "date_of_issue")
    private java.time.LocalDate dateOfIssue;

    @Column(name = "card_place", length = 100)
    private String cardPlace;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "current_address", length = 300)
    private String currentAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_program_id")
    private TrainingProgram trainingProgram;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_classe_id")
    private StudentClass studentClass;

    @Column(name = "admission_year")
    private java.time.LocalDateTime admissionYear;

    public Student() {}

    // Backward compatibility for existing UI
    public String getStudentId() { return code; }
    public void setStudentId(String studentId) { this.code = studentId; }
    public String getName() { return fullName; }
    public void setName(String name) { this.fullName = name; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    // ===== Getter Setter =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public java.time.LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(java.time.LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPersonalIdentificationNumber() { return personalIdentificationNumber; }
    public void setPersonalIdentificationNumber(String personalIdentificationNumber) { this.personalIdentificationNumber = personalIdentificationNumber; }

    public java.time.LocalDate getDateOfIssue() { return dateOfIssue; }
    public void setDateOfIssue(java.time.LocalDate dateOfIssue) { this.dateOfIssue = dateOfIssue; }

    public String getCardPlace() { return cardPlace; }
    public void setCardPlace(String cardPlace) { this.cardPlace = cardPlace; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCurrentAddress() { return currentAddress; }
    public void setCurrentAddress(String currentAddress) { this.currentAddress = currentAddress; }

    public AcademicYear getAcademicYear() { return academicYear; }
    public void setAcademicYear(AcademicYear academicYear) { this.academicYear = academicYear; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Major getMajor() { return major; }
    public void setMajor(Major major) { this.major = major; }

    public TrainingProgram getTrainingProgram() { return trainingProgram; }
    public void setTrainingProgram(TrainingProgram trainingProgram) { this.trainingProgram = trainingProgram; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public StudentClass getStudentClass() { return studentClass; }
    public void setStudentClass(StudentClass studentClass) { this.studentClass = studentClass; }

    public java.time.LocalDateTime getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(java.time.LocalDateTime admissionYear) { this.admissionYear = admissionYear; }
}


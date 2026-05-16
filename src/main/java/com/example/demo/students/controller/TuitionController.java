package com.example.demo.students.controller;

import com.example.demo.students.model.entity.Payment;
import com.example.demo.students.model.entity.StudentTuition;
import com.example.demo.students.model.entity.TuitionFee;
import com.example.demo.students.repository.PaymentRepository;
import com.example.demo.students.repository.StudentTuitionRepository;
import com.example.demo.students.repository.TuitionFeeRepository;
import com.example.demo.students.repository.StudentRepository;
import com.example.demo.students.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TuitionController {

    @Autowired
    private StudentTuitionRepository studentTuitionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    public static class TuitionDTO {
        public String studentId;
        public String studentCode;
        public UUID semesterId;
        public Integer totalCredits;
        public BigDecimal rawAmount;
        public BigDecimal netAmount;
        public BigDecimal paidAmount;
        public BigDecimal debtAmount;
        public Short status;
        public LocalDate deadline;
        public Boolean isActive;
    }

    // --- Student Tuition Endpoints ---
    @GetMapping("/tuitions")
    public List<StudentTuition> getAllTuitions() {
        return studentTuitionRepository.findAll();
    }

    @GetMapping("/tuitions/search")
    public List<StudentTuition> searchTuitions(@RequestParam String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentTuitionRepository.findAll();
        }
        return studentTuitionRepository.findByStudent_CodeContainingIgnoreCase(keyword.trim());
    }

    @GetMapping("/tuitions/statistics")
    public Map<String, BigDecimal> getStatistics() {
        List<StudentTuition> all = studentTuitionRepository.findAll();
        BigDecimal expected = all.stream().map(StudentTuition::getNetAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal paid = all.stream().map(StudentTuition::getPaidAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal debt = all.stream().map(StudentTuition::getDebtAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        Map<String, BigDecimal> stats = new HashMap<>();
        stats.put("expected", expected);
        stats.put("paid", paid);
        stats.put("debt", debt);
        return stats;
    }

    @GetMapping("/tuitions/{id:[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}}")
    public ResponseEntity<StudentTuition> getTuitionById(@PathVariable UUID id) {
        Optional<StudentTuition> tuition = studentTuitionRepository.findById(id);
        return tuition.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/tuitions")
    public ResponseEntity<?> createTuition(@RequestBody TuitionDTO dto) {
        String code = dto.studentCode != null ? dto.studentCode : dto.studentId;
        Optional<Student> studentOpt = studentRepository.findByCode(code);
        if (!studentOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Student not found: " + code);
        }
        
        StudentTuition tuition = new StudentTuition();
        tuition.setStudent(studentOpt.get());
        tuition.setSemesterId(dto.semesterId);
        tuition.setTotalCredits(dto.totalCredits);
        tuition.setRawAmount(dto.rawAmount);
        tuition.setNetAmount(dto.netAmount);
        tuition.setPaidAmount(dto.paidAmount);
        tuition.setDebtAmount(dto.debtAmount);
        tuition.setStatus(dto.status);
        tuition.setDeadline(dto.deadline);
        tuition.setIsActive(dto.isActive);
        
        return ResponseEntity.ok(studentTuitionRepository.save(tuition));
    }

    @PutMapping("/tuitions/{id}")
    public ResponseEntity<?> updateTuition(@PathVariable UUID id, @RequestBody TuitionDTO dto) {
        return studentTuitionRepository.findById(id)
                .map(tuition -> {
                    String code = dto.studentCode != null ? dto.studentCode : dto.studentId;
                    Optional<Student> studentOpt = studentRepository.findByCode(code);
                    if (studentOpt.isPresent()) {
                        tuition.setStudent(studentOpt.get());
                    } else {
                        throw new IllegalArgumentException("Student not found: " + code);
                    }
                    tuition.setSemesterId(dto.semesterId);
                    tuition.setTotalCredits(dto.totalCredits);
                    tuition.setRawAmount(dto.rawAmount);
                    tuition.setNetAmount(dto.netAmount);
                    tuition.setPaidAmount(dto.paidAmount);
                    tuition.setDebtAmount(dto.debtAmount);
                    tuition.setStatus(dto.status);
                    tuition.setDeadline(dto.deadline);
                    tuition.setIsActive(dto.isActive);
                    return ResponseEntity.ok(studentTuitionRepository.save(tuition));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tuitions/{id}")
    public ResponseEntity<Void> deleteTuition(@PathVariable UUID id) {
        if (studentTuitionRepository.existsById(id)) {
            studentTuitionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // --- Payments Endpoints ---
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/tuitions/{id}/payments")
    public ResponseEntity<List<Payment>> getPaymentsByTuitionId(@PathVariable UUID id) {
        if (!studentTuitionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Payment> payments = paymentRepository.findByStudentTuition_IdOrderByPaymentDateDesc(id);
        return ResponseEntity.ok(payments);
    }

    public static class PaymentDTO {
        public UUID tuitionId;
        public StudentTuition studentTuition;
        public BigDecimal amountPaid;
        public Short paymentMethod;
        public String paymentStatus;
        public String notes;
    }

    @PostMapping("/payments")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO dto) {
        UUID tId = dto.tuitionId != null ? dto.tuitionId : (dto.studentTuition != null ? dto.studentTuition.getId() : null);
        if (tId == null) {
            return ResponseEntity.badRequest().body("Tuition ID is required");
        }
        
        Optional<StudentTuition> tuitionOpt = studentTuitionRepository.findById(tId);
        if (!tuitionOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Tuition not found");
        }
        
        StudentTuition tuition = tuitionOpt.get();
        
        Payment payment = new Payment();
        payment.setStudentTuition(tuition);
        payment.setAmountPaid(dto.amountPaid);
        payment.setPaymentMethod(dto.paymentMethod);
        payment.setPaymentStatus(dto.paymentStatus != null ? dto.paymentStatus : "SUCCESS");
        payment.setNotes(dto.notes);
        
        // Save the payment
        payment.setPaymentDate(java.time.LocalDateTime.now());
        Payment savedPayment = paymentRepository.save(payment);
        
        // Update the tuition record
        BigDecimal currentPaid = tuition.getPaidAmount() != null ? tuition.getPaidAmount() : BigDecimal.ZERO;
        BigDecimal newPaid = currentPaid.add(payment.getAmountPaid());
        tuition.setPaidAmount(newPaid);
        
        BigDecimal netAmount = tuition.getNetAmount() != null ? tuition.getNetAmount() : BigDecimal.ZERO;
        BigDecimal newDebt = netAmount.subtract(newPaid);
        if (newDebt.compareTo(BigDecimal.ZERO) < 0) {
            newDebt = BigDecimal.ZERO;
        }
        tuition.setDebtAmount(newDebt);
        
        // Update status
        if (newPaid.compareTo(netAmount) >= 0) {
            tuition.setStatus((short) 1); // PAID
        } else if (newPaid.compareTo(BigDecimal.ZERO) > 0) {
            tuition.setStatus((short) 2); // PARTIAL
        } else {
            tuition.setStatus((short) 3); // DEBT
        }
        
        studentTuitionRepository.save(tuition);
        
        return ResponseEntity.ok(savedPayment);
    }

    // --- Tuition Fees Endpoints ---
    @GetMapping("/tuition-fees")
    public List<TuitionFee> getAllTuitionFees() {
        return tuitionFeeRepository.findAll();
    }
}

package com.example.demo.students.controller;

import com.example.demo.students.model.entity.Payment;
import com.example.demo.students.model.entity.StudentTuition;
import com.example.demo.students.model.entity.TuitionFee;
import com.example.demo.students.repository.PaymentRepository;
import com.example.demo.students.repository.StudentTuitionRepository;
import com.example.demo.students.repository.TuitionFeeRepository;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TuitionController {

    @Autowired
    private StudentTuitionRepository studentTuitionRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

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
        return studentTuitionRepository.findByStudentIdContainingIgnoreCase(keyword.trim());
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
    public StudentTuition createTuition(@RequestBody StudentTuition tuition) {
        return studentTuitionRepository.save(tuition);
    }

    @PutMapping("/tuitions/{id}")
    public ResponseEntity<StudentTuition> updateTuition(@PathVariable UUID id, @RequestBody StudentTuition updatedTuition) {
        return studentTuitionRepository.findById(id)
                .map(tuition -> {
                    tuition.setStudentId(updatedTuition.getStudentId());
                    tuition.setSemesterId(updatedTuition.getSemesterId());
                    tuition.setTotalCredits(updatedTuition.getTotalCredits());
                    tuition.setRawAmount(updatedTuition.getRawAmount());
                    tuition.setNetAmount(updatedTuition.getNetAmount());
                    tuition.setPaidAmount(updatedTuition.getPaidAmount());
                    tuition.setDebtAmount(updatedTuition.getDebtAmount());
                    tuition.setStatus(updatedTuition.getStatus());
                    tuition.setDeadline(updatedTuition.getDeadline());
                    tuition.setIsActive(updatedTuition.getIsActive());
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

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    // --- Tuition Fees Endpoints ---
    @GetMapping("/tuition-fees")
    public List<TuitionFee> getAllTuitionFees() {
        return tuitionFeeRepository.findAll();
    }
}

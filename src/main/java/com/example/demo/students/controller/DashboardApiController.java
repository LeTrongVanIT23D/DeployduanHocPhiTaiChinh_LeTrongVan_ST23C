package com.example.demo.students.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.students.repository.StudentRepository;
import com.example.demo.students.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardApiController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public DashboardApiController(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long totalStudents = studentRepository.count();
        long totalUsers = userRepository.count();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents", totalStudents);
        stats.put("bounceRate", 53); // Mocked for UI template matching
        stats.put("totalUsers", totalUsers);
        stats.put("visitorsCount", 65); // Mocked for UI template matching
        
        return stats;
    }
}


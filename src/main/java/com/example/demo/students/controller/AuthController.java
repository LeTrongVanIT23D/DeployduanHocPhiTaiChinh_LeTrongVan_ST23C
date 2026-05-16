package com.example.demo.students.controller;

import com.example.demo.students.model.dto.LoginRequest;
import com.example.demo.students.model.dto.RegisterRequest;
import com.example.demo.students.model.entity.Role;
import com.example.demo.students.model.entity.User;
import com.example.demo.students.repository.RoleRepository;
import com.example.demo.students.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        List<User> users = userRepository.findByUsername(request.getUsername());
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Sai tài khoản hoặc mật khẩu"));
        }

        User user = users.get(0);
        // Simple plain-text password check (for demo purposes as requested)
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Sai tài khoản hoặc mật khẩu"));
        }

        if (user.getIsActive() != null && !user.getIsActive()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Tài khoản đã bị khóa"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());
        
        // Extract roles
        List<String> roles = user.getRoles().stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Kiểm tra username đã tồn tại chưa
        if (!userRepository.findByUsername(request.getUsername()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Tên đăng nhập đã tồn tại"));
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword()); // Không dùng encoder theo yêu cầu hiện tại
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setIsActive(true);

        // Mặc định gán quyền STUDENT cho user tự đăng ký
        Optional<Role> studentRoleOpt = roleRepository.findByCode("STUDENT");
        if (studentRoleOpt.isPresent()) {
            newUser.getRoles().add(studentRoleOpt.get());
        }

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Đăng ký thành công!"));
    }
}

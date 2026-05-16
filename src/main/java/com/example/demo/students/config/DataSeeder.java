package com.example.demo.students.config;

import com.example.demo.students.model.entity.Role;
import com.example.demo.students.model.entity.User;
import com.example.demo.students.repository.RoleRepository;
import com.example.demo.students.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            // 1. Tạo các roles
            Role superAdminRole = getOrCreateRole(roleRepository, "SUPER_ADMIN", "Super Admin");
            Role teacherRole = getOrCreateRole(roleRepository, "TEACHER", "Giảng viên");
            Role studentRole = getOrCreateRole(roleRepository, "STUDENT", "Sinh viên");

            // 2. Tạo người dùng
            // Super_admin: user09 / hash123
            createOrUpdateUser(userRepository, "user09", "hash123", "Super Admin User", superAdminRole);

            // Giảng viên: user07 / hash123
            createOrUpdateUser(userRepository, "user07", "hash123", "Giảng viên User", teacherRole);

            // Sinh viên: user01 / hash123
            createOrUpdateUser(userRepository, "user01", "hash123", "Sinh viên User", studentRole);
        };
    }

    private Role getOrCreateRole(RoleRepository repo, String code, String name) {
        Optional<Role> existing = repo.findByCode(code);
        if (existing.isPresent()) {
            return existing.get();
        }
        Role role = new Role();
        role.setCode(code);
        role.setName(name);
        role.setIsSystem(true);
        return repo.save(role);
    }

    private void createOrUpdateUser(UserRepository repo, String username, String password, String fullName, Role role) {
        List<User> users = repo.findByUsername(username);
        if (users.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // Thực tế nên dùng password encoder, ở đây dùng plain text theo yêu cầu "hash123"
            user.setFullName(fullName);
            user.getRoles().add(role);
            repo.save(user);
        } else {
            User existing = users.get(0);
            // Cập nhật role nếu chưa có
            if (!existing.getRoles().contains(role)) {
                existing.getRoles().add(role);
                repo.save(existing);
            }
        }
    }
}

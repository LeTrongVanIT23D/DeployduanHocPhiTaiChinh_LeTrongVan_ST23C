package com.example.demo.students.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.students.model.entity.Role;
import com.example.demo.students.model.entity.User;
import com.example.demo.students.repository.RoleRepository;
import com.example.demo.students.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    // @Autowired
    // private PasswordEncoder encoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public User createUser(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setIsActive(true);
        
        return userRepo.save(u);
    }

    public User assignRole(UUID userId, UUID roleId) {
        User u = userRepo.findById(userId).orElseThrow();
        Role r = roleRepo.findById(roleId).orElseThrow();
        u.getRoles().add(r);
        return userRepo.save(u);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User getUserById(UUID id) {
        return userRepo.findById(id).orElse(null);
    }

    public User removeRole(UUID userId, UUID roleId) {
        User u = userRepo.findById(userId).orElseThrow();
        Role r = roleRepo.findById(roleId).orElseThrow();
        u.getRoles().remove(r);
        return userRepo.save(u);
    }

    public User updateProfile(UUID userId, User updatedUser) {
        User u = userRepo.findById(userId).orElseThrow();
        u.setFullName(updatedUser.getFullName());
        u.setEmail(updatedUser.getEmail());
        u.setPhone(updatedUser.getPhone());
        if (updatedUser.getAvatarUrl() != null) {
            u.setAvatarUrl(updatedUser.getAvatarUrl());
        }
        // Có thể cho phép đổi mật khẩu nếu truyền lên
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            u.setPassword(updatedUser.getPassword());
        }
        return userRepo.save(u);
    }
}



package com.example.demo.students.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.students.model.entity.User;
import com.example.demo.students.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    // @PreAuthorize("hasAuthority('USER_MANAGE')")
    @PostMapping
    public User create(@RequestParam String username,
                       @RequestParam String password) {
        return userService.createUser(username, password);
    }

    @PostMapping("/create-with-details")
    public User createWithDetails(@org.springframework.web.bind.annotation.RequestBody User user) {
        User newUser = userService.createUser(user.getUsername(), user.getPassword());
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        return userService.updateProfile(newUser.getId(), newUser);
    }

    // @PreAuthorize("hasAuthority('USER_MANAGE')")
    @PostMapping("/{userId}/roles/{roleId}")
    public User assignRole(@PathVariable UUID userId,
                           @PathVariable UUID roleId) {
        return userService.assignRole(userId, roleId);
    }

    // @PreAuthorize("hasAuthority('USER_MANAGE')")
    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public User updateProfile(@PathVariable UUID id, @org.springframework.web.bind.annotation.RequestBody User updatedUser) {
        return userService.updateProfile(id, updatedUser);
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{userId}/roles/{roleId}")
    public User removeRole(@PathVariable UUID userId, @PathVariable UUID roleId) {
        return userService.removeRole(userId, roleId);
    }
}

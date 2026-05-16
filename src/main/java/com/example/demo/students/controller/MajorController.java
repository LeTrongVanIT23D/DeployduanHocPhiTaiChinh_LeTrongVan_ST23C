package com.example.demo.students.controller;

import com.example.demo.students.model.entity.Major;
import com.example.demo.students.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/majors")
@CrossOrigin(origins = "*")
public class MajorController {

    private final MajorService service;

    public MajorController(MajorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Major> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Major> getById(@PathVariable UUID id) {
        Major major = service.getById(id);
        return major != null ? ResponseEntity.ok(major) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Major create(@RequestBody Major major) {
        return service.create(major);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Major> update(@PathVariable UUID id, @RequestBody Major major) {
        Major updated = service.update(id, major);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Major> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}

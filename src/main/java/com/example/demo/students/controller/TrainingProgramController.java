package com.example.demo.students.controller;

import com.example.demo.students.model.entity.TrainingProgram;
import com.example.demo.students.service.TrainingProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/training-programs")
@CrossOrigin(origins = "*")
public class TrainingProgramController {

    private final TrainingProgramService service;

    public TrainingProgramController(TrainingProgramService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrainingProgram> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgram> getById(@PathVariable UUID id) {
        TrainingProgram program = service.getById(id);
        return program != null ? ResponseEntity.ok(program) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public TrainingProgram create(@RequestBody TrainingProgram program) {
        return service.create(program);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgram> update(@PathVariable UUID id, @RequestBody TrainingProgram program) {
        TrainingProgram updated = service.update(id, program);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<TrainingProgram> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}

package com.example.demo.students.controller;

import com.example.demo.students.model.entity.Position;
import com.example.demo.students.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/positions")
@CrossOrigin(origins = "*")
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Position> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable UUID id) {
        Position position = service.getById(id);
        return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Position create(@RequestBody Position position) {
        return service.create(position);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable UUID id, @RequestBody Position position) {
        Position updated = service.update(id, position);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Position> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}

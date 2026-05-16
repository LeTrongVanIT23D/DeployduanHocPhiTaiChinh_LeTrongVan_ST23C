package com.example.demo.students.service;

import com.example.demo.students.model.entity.Position;
import com.example.demo.students.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PositionService {

    private final PositionRepository repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public List<Position> getAll() {
        return repository.findAll();
    }

    public Position getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Position create(Position position) {
        return repository.save(position);
    }

    public Position update(UUID id, Position updated) {
        Position existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setLevel(updated.getLevel());
        
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            existing.setDepartment(updated.getDepartment());
        }
        
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Position> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

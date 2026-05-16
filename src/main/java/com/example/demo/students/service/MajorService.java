package com.example.demo.students.service;

import com.example.demo.students.model.entity.Major;
import com.example.demo.students.repository.MajorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MajorService {

    private final MajorRepository repository;

    public MajorService(MajorRepository repository) {
        this.repository = repository;
    }

    public List<Major> getAll() {
        return repository.findAll();
    }

    public Major getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Major create(Major major) {
        return repository.save(major);
    }

    @Transactional
    public Major update(UUID id, Major updated) {
        Major existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            existing.setDepartment(updated.getDepartment());
        }
        
        existing.setEffectiveDate(updated.getEffectiveDate());
        existing.setExpiryDate(updated.getExpiryDate());

        return repository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Major> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

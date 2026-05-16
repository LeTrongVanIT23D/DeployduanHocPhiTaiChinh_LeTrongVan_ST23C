package com.example.demo.students.service;

import com.example.demo.students.model.entity.Department;
import com.example.demo.students.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Department create(Department department) {
        return repository.save(department);
    }

    public Department update(UUID id, Department updated) {
        Department existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setEstablishedDate(updated.getEstablishedDate());
        existing.setIsActive(updated.getIsActive());
        
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Department> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

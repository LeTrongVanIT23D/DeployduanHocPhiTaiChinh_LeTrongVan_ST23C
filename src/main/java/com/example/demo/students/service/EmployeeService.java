package com.example.demo.students.service;

import com.example.demo.students.model.entity.Employee;
import com.example.demo.students.model.entity.EmployeePosition;
import com.example.demo.students.repository.EmployeeRepository;
import com.example.demo.students.repository.EmployeePositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeePositionRepository positionRepository;

    public EmployeeService(EmployeeRepository repository, EmployeePositionRepository positionRepository) {
        this.repository = repository;
        this.positionRepository = positionRepository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee update(UUID id, Employee updated) {
        Employee existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setFullName(updated.getFullName());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setGender(updated.getGender());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            existing.setDepartment(updated.getDepartment());
        }
        
        if (updated.getPosition() != null && updated.getPosition().getId() != null) {
            existing.setPosition(updated.getPosition());
        }
        
        existing.setHireDate(updated.getHireDate());
        existing.setContractType(updated.getContractType());
        existing.setSalaryCoefficient(updated.getSalaryCoefficient());
        existing.setAcademicDegree(updated.getAcademicDegree());
        existing.setAcademicTitle(updated.getAcademicTitle());
        existing.setSpecialization(updated.getSpecialization());

        return repository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }

    // --- Employee Position History ---
    public List<EmployeePosition> getPositions(UUID employeeId) {
        return positionRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    public EmployeePosition addPosition(EmployeePosition empPosition) {
        return positionRepository.save(empPosition);
    }

    @Transactional
    public void removePosition(UUID positionHistoryId) {
        positionRepository.deleteById(positionHistoryId);
    }
}

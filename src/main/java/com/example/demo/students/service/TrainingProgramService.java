package com.example.demo.students.service;

import com.example.demo.students.model.entity.TrainingProgram;
import com.example.demo.students.repository.TrainingProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TrainingProgramService {

    private final TrainingProgramRepository repository;

    public TrainingProgramService(TrainingProgramRepository repository) {
        this.repository = repository;
    }

    public List<TrainingProgram> getAll() {
        return repository.findAll();
    }

    public TrainingProgram getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public TrainingProgram create(TrainingProgram program) {
        return repository.save(program);
    }

    @Transactional
    public TrainingProgram update(UUID id, TrainingProgram updated) {
        TrainingProgram existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setNameEn(updated.getNameEn());
        
        if (updated.getMajor() != null && updated.getMajor().getId() != null) {
            existing.setMajor(updated.getMajor());
        }
        if (updated.getAcademicYear() != null && updated.getAcademicYear().getId() != null) {
            existing.setAcademicYear(updated.getAcademicYear());
        }
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            existing.setDepartment(updated.getDepartment());
        }
        
        existing.setDegreeLevel(updated.getDegreeLevel());
        existing.setEducationType(updated.getEducationType());
        existing.setTotalCredits(updated.getTotalCredits());
        existing.setRequiredCredits(updated.getRequiredCredits());
        existing.setElectiveCredits(updated.getElectiveCredits());
        existing.setInternshipCredits(updated.getInternshipCredits());
        existing.setThesisCredits(updated.getThesisCredits());
        existing.setAdmissionYear(updated.getAdmissionYear());
        existing.setDurationYears(updated.getDurationYears());
        existing.setMaxDurationYears(updated.getMaxDurationYears());
        existing.setEffectiveDate(updated.getEffectiveDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setDescription(updated.getDescription());
        existing.setObjectives(updated.getObjectives());
        existing.setLearningOutcomes(updated.getLearningOutcomes());
        existing.setVersion(updated.getVersion());
        existing.setStatus(updated.getStatus());

        return repository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<TrainingProgram> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

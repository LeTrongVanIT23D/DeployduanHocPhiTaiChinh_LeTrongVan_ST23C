package com.example.demo.students.service;

import com.example.demo.students.model.entity.Course;
import com.example.demo.students.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Course create(Course course) {
        return repository.save(course);
    }

    @Transactional
    public Course update(UUID id, Course updated) {
        Course existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setNameEn(updated.getNameEn());
        
        if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
            existing.setDepartment(updated.getDepartment());
        }
        
        existing.setCredits(updated.getCredits());
        existing.setCourseType(updated.getCourseType());
        existing.setTheoryHours(updated.getTheoryHours());
        existing.setPracticeHours(updated.getPracticeHours());
        existing.setSelfStudyHours(updated.getSelfStudyHours());
        existing.setInternshipCredits(updated.getInternshipCredits());
        existing.setDescription(updated.getDescription());

        return repository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Course> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}

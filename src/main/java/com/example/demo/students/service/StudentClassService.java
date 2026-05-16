package com.example.demo.students.service;
import com.example.demo.students.model.entity.StudentClass;
import com.example.demo.students.repository.StudentClassRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class StudentClassService {
    private final StudentClassRepository repo;
    public StudentClassService(StudentClassRepository repo){this.repo = repo;}
    public List<StudentClass> getAll(){return repo.findAll();}
    public StudentClass getById(UUID id){return repo.findById(id).orElse(null);}
    public StudentClass create(StudentClass obj){return repo.save(obj);}
    public StudentClass update(UUID id, StudentClass updated){
        StudentClass e = getById(id); if(e==null)return null;
        e.setCode(updated.getCode()); e.setName(updated.getName());
        if(updated.getAcademicYear() != null) e.setAcademicYear(updated.getAcademicYear());
        if(updated.getDepartment() != null) e.setDepartment(updated.getDepartment());
        if(updated.getMajor() != null) e.setMajor(updated.getMajor());
        if(updated.getTrainingProgram() != null) e.setTrainingProgram(updated.getTrainingProgram());
        if(updated.getEmployee() != null) e.setEmployee(updated.getEmployee());
        return repo.save(e);
    }
    public void delete(UUID id){repo.deleteById(id);}
}

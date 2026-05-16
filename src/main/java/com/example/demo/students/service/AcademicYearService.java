package com.example.demo.students.service;
import com.example.demo.students.model.entity.AcademicYear;
import com.example.demo.students.repository.AcademicYearRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class AcademicYearService {
    private final AcademicYearRepository repo;
    public AcademicYearService(AcademicYearRepository repo){this.repo = repo;}
    public List<AcademicYear> getAll(){return repo.findAll();}
    public AcademicYear getById(UUID id){return repo.findById(id).orElse(null);}
    public AcademicYear create(AcademicYear obj){return repo.save(obj);}
    public AcademicYear update(UUID id, AcademicYear updated){
        AcademicYear e = getById(id); if(e==null)return null;
        e.setCode(updated.getCode()); e.setName(updated.getName()); e.setYear(updated.getYear());
        e.setDescription(updated.getDescription()); e.setStartDate(updated.getStartDate()); e.setEndDate(updated.getEndDate());
        return repo.save(e);
    }
    public void delete(UUID id){repo.deleteById(id);}
}

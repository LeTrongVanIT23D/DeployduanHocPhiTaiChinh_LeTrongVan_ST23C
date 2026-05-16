package com.example.demo.students.service;
import com.example.demo.students.model.entity.Semester;
import com.example.demo.students.repository.SemesterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class SemesterService {
    private final SemesterRepository repo;
    public SemesterService(SemesterRepository repo){this.repo = repo;}
    public List<Semester> getAll(){return repo.findAll();}
    public Semester getById(UUID id){return repo.findById(id).orElse(null);}
    public Semester create(Semester obj){return repo.save(obj);}
    public Semester update(UUID id, Semester updated){
        Semester e = getById(id); if(e==null)return null;
        e.setCode(updated.getCode()); e.setName(updated.getName());
        e.setSchoolYearName(updated.getSchoolYearName());
        if(updated.getSchoolYear() != null) e.setSchoolYear(updated.getSchoolYear());
        e.setStartDate(updated.getStartDate()); e.setEndDate(updated.getEndDate());
        return repo.save(e);
    }
    public void delete(UUID id){repo.deleteById(id);}
}

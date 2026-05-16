package com.example.demo.students.service;
import com.example.demo.students.model.entity.SchoolYear;
import com.example.demo.students.repository.SchoolYearRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class SchoolYearService {
    private final SchoolYearRepository repo;
    public SchoolYearService(SchoolYearRepository repo){this.repo = repo;}
    public List<SchoolYear> getAll(){return repo.findAll();}
    public SchoolYear getById(UUID id){return repo.findById(id).orElse(null);}
    public SchoolYear create(SchoolYear obj){return repo.save(obj);}
    public SchoolYear update(UUID id, SchoolYear updated){
        SchoolYear e = getById(id); if(e==null)return null;
        e.setCode(updated.getCode()); e.setName(updated.getName()); e.setNote(updated.getNote());
        e.setDescription(updated.getDescription()); e.setStartDate(updated.getStartDate()); e.setEndDate(updated.getEndDate());
        return repo.save(e);
    }
    public void delete(UUID id){repo.deleteById(id);}
}

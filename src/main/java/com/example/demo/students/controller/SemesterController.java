package com.example.demo.students.controller;
import com.example.demo.students.model.entity.Semester;
import com.example.demo.students.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/semesters") @CrossOrigin("*")
public class SemesterController {
    private final SemesterService service;
    public SemesterController(SemesterService service){this.service=service;}
    @GetMapping public List<Semester> getAll(){return service.getAll();}
    @PostMapping public Semester create(@RequestBody Semester obj){return service.create(obj);}
    @PutMapping("/{id}") public ResponseEntity<Semester> update(@PathVariable UUID id, @RequestBody Semester obj){
        Semester u = service.update(id, obj); return u!=null?ResponseEntity.ok(u):ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable UUID id){service.delete(id);}
}

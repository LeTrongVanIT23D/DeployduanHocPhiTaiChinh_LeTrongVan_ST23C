package com.example.demo.students.controller;
import com.example.demo.students.model.entity.AcademicYear;
import com.example.demo.students.service.AcademicYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/academic-years") @CrossOrigin("*")
public class AcademicYearController {
    private final AcademicYearService service;
    public AcademicYearController(AcademicYearService service){this.service=service;}
    @GetMapping public List<AcademicYear> getAll(){return service.getAll();}
    @PostMapping public AcademicYear create(@RequestBody AcademicYear obj){return service.create(obj);}
    @PutMapping("/{id}") public ResponseEntity<AcademicYear> update(@PathVariable UUID id, @RequestBody AcademicYear obj){
        AcademicYear u = service.update(id, obj); return u!=null?ResponseEntity.ok(u):ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable UUID id){service.delete(id);}
}

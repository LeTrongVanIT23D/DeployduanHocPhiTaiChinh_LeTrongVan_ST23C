package com.example.demo.students.controller;
import com.example.demo.students.model.entity.StudentClass;
import com.example.demo.students.service.StudentClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/student-classes") @CrossOrigin("*")
public class StudentClassController {
    private final StudentClassService service;
    public StudentClassController(StudentClassService service){this.service=service;}
    @GetMapping public List<StudentClass> getAll(){return service.getAll();}
    @PostMapping public StudentClass create(@RequestBody StudentClass obj){return service.create(obj);}
    @PutMapping("/{id}") public ResponseEntity<StudentClass> update(@PathVariable UUID id, @RequestBody StudentClass obj){
        StudentClass u = service.update(id, obj); return u!=null?ResponseEntity.ok(u):ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable UUID id){service.delete(id);}
}

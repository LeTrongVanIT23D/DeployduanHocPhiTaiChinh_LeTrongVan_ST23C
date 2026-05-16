package com.example.demo.students.controller;
import com.example.demo.students.model.entity.SchoolYear;
import com.example.demo.students.service.SchoolYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/school-years") @CrossOrigin("*")
public class SchoolYearController {
    private final SchoolYearService service;
    public SchoolYearController(SchoolYearService service){this.service=service;}
    @GetMapping public List<SchoolYear> getAll(){return service.getAll();}
    @PostMapping public SchoolYear create(@RequestBody SchoolYear obj){return service.create(obj);}
    @PutMapping("/{id}") public ResponseEntity<SchoolYear> update(@PathVariable UUID id, @RequestBody SchoolYear obj){
        SchoolYear u = service.update(id, obj); return u!=null?ResponseEntity.ok(u):ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable UUID id){service.delete(id);}
}

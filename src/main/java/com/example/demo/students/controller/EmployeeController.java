package com.example.demo.students.controller;

import com.example.demo.students.model.entity.Employee;
import com.example.demo.students.model.entity.EmployeePosition;
import com.example.demo.students.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        Employee employee = service.getById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.create(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody Employee employee) {
        Employee updated = service.update(id, employee);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    // --- Employee Position History ---
    @GetMapping("/{id}/positions")
    public List<EmployeePosition> getPositions(@PathVariable UUID id) {
        return service.getPositions(id);
    }

    @PostMapping("/{id}/positions")
    public EmployeePosition addPosition(@PathVariable UUID id, @RequestBody EmployeePosition position) {
        Employee emp = new Employee();
        emp.setId(id);
        position.setEmployee(emp);
        return service.addPosition(position);
    }

    @DeleteMapping("/{id}/positions/{posId}")
    public ResponseEntity<Void> removePosition(@PathVariable UUID id, @PathVariable UUID posId) {
        service.removePosition(posId);
        return ResponseEntity.ok().build();
    }
}

package org.example.search.gateway.emp_dep.controller;

import org.example.search.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.example.search.gateway.emp_dep.service.entityManager.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/em/departments")
public class DepartmentEMController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentEMController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
        List<DepartmentEntity> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        DepartmentEntity department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<Long> createDepartment(@RequestBody DepartmentEntity department) {
        DepartmentEntity savedDepartment = departmentService.save(department);
        return new ResponseEntity<>(savedDepartment.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDepartment(@PathVariable Long id, @RequestBody DepartmentEntity department) {
        Long updatedDepartment = departmentService.update(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package org.example.search.gateway.emp_dep.controller;

import org.example.search.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.example.search.gateway.emp_dep.service.repository.DepartmentRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepoService departmentService;

    @Autowired
    public DepartmentController(DepartmentRepoService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDepartment(@RequestBody DepartmentEntity department) {
        DepartmentEntity savedDepartment = departmentService.save(department);
        return new ResponseEntity<>(savedDepartment.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDepartment(@PathVariable Long id, @RequestBody DepartmentEntity department) {
        DepartmentEntity currentDepartment = departmentService.findById(id);
        if (currentDepartment != null) {
            department.setId(id);
            departmentService.save(department);
            return new ResponseEntity<>(currentDepartment.getId(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

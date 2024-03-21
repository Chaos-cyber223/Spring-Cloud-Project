package org.example.gateway.emp_dep.controller;

import org.example.gateway.emp_dep.pojo.dto.EmployeeDTO;
import org.example.gateway.emp_dep.pojo.dto.EmployeeNameUpdateDTO;
import org.example.gateway.emp_dep.pojo.entity.EmployeeEntity;
import org.example.gateway.emp_dep.service.entityManager.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/em/employees")
public class EmployeeEMController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeEMController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> employees = employeeService.findAllEmployeesWithDepartments();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Long> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity savedEmployee = employeeService.save(employeeDTO);
        return new ResponseEntity<>(savedEmployee.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/updateName")
    public ResponseEntity<Long> updateEmployeeName(@PathVariable Long id, @RequestBody EmployeeNameUpdateDTO updateDTO) {
        EmployeeEntity updatedEmployee = employeeService.updateEmployeeName(id, updateDTO.getName());
        return new ResponseEntity<>(updatedEmployee.getId(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeEntity>> findByAgeAndDepartmentName(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "departmentName", required = false) String departmentName) {
        List<EmployeeEntity> employees = employeeService.findEmployeesByAgeAndDepartmentName(age, departmentName);
        return ResponseEntity.ok(employees);
    }
}


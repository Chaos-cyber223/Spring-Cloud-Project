package org.example.gateway.emp_dep.service.entityManager;

import org.example.gateway.emp_dep.pojo.dto.EmployeeDTO;
import org.example.gateway.emp_dep.pojo.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> findAllEmployeesWithDepartments();

    EmployeeEntity findById(Long id);
    EmployeeEntity save(EmployeeDTO employeeDTO);
    void delete(Long id);
    EmployeeEntity updateEmployeeName(Long id, String name);
    public List<EmployeeEntity> findEmployeesByAgeAndDepartmentName(Integer age, String departmentName);
}


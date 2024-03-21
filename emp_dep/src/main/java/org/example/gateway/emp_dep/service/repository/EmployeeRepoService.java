package org.example.gateway.emp_dep.service.repository;

import org.example.gateway.emp_dep.pojo.dto.EmployeeDTO;
import org.example.gateway.emp_dep.pojo.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepoService {

    List<EmployeeEntity> findAllWithDepartment();

    EmployeeEntity findById(Long id);

    EmployeeEntity save(EmployeeDTO employeeDTO);

    void delete(Long id);
    List<EmployeeEntity> findByAgeGreaterThan(Integer age);
    void updateEmployeeName(Long id, String name);

}

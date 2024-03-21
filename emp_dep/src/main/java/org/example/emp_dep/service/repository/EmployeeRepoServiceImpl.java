package org.example.emp_dep.service.repository;

import org.example.emp_dep.pojo.dto.EmployeeDTO;
import org.example.emp_dep.pojo.entity.DepartmentEntity;
import org.example.emp_dep.pojo.entity.EmployeeEntity;
import org.example.emp_dep.repository.DepartmentRepository;
import org.example.emp_dep.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRepoServiceImpl implements EmployeeRepoService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeRepoServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<EmployeeEntity> findAllWithDepartment() {
        return employeeRepository.findAllWithDepartment();
    }

    @Override
    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }


    @Override
    @Transactional
    public EmployeeEntity save(EmployeeDTO employeeDTO) {
        DepartmentEntity department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }


    
    @Override
    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeEntity> findByAgeGreaterThan(Integer age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    @Override
    @Transactional
    public void updateEmployeeName(Long id, String name) {
        employeeRepository.updateEmployeeName(id, name);
    }

}


package org.example.gateway.emp_dep.service.entityManager;

import org.example.gateway.emp_dep.pojo.dto.EmployeeDTO;
import org.example.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.example.gateway.emp_dep.pojo.entity.EmployeeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findAllEmployeesWithDepartments() {
        return entityManager.createQuery(
                        "SELECT e FROM EmployeeEntity e JOIN FETCH e.department", EmployeeEntity.class)
                .getResultList();
    }


    @Override
    public EmployeeEntity findById(Long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeDTO employeeDTO) {
        DepartmentEntity department = entityManager.find(DepartmentEntity.class, employeeDTO.getDepartmentId());
        if (department == null) {
            throw new RuntimeException("Department not found");
        }

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(department);

        entityManager.persist(employee);
        return employee;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }


    @Override
    @Transactional
    public EmployeeEntity updateEmployeeName(Long id, String name) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, id);
        if (employee != null) {
            employee.setName(name);
            entityManager.merge(employee);
            return employee;
        }
        return null;
    }

    @Override
    public List<EmployeeEntity> findEmployeesByAgeAndDepartmentName(Integer age, String departmentName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> cq = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> employee = cq.from(EmployeeEntity.class);
        Join<EmployeeEntity, DepartmentEntity> department = employee.join("department");

        List<Predicate> predicates = new ArrayList<>();
        if (age != null) {
            predicates.add(cb.greaterThanOrEqualTo(employee.get("age"), age));
        }
        if (departmentName != null && !departmentName.isEmpty()) {
            predicates.add(cb.equal(department.get("name"), departmentName));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

}

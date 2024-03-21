package org.example.gateway.emp_dep.service.entityManager;

import org.example.gateway.emp_dep.pojo.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {
    List<DepartmentEntity> findAll();
    DepartmentEntity findById(Long id);
    DepartmentEntity save(DepartmentEntity department);
    void delete(Long id);
    Long update(Long id, DepartmentEntity department);
}


package org.example.search.gateway.emp_dep.service.repository;

import org.example.search.gateway.emp_dep.pojo.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentRepoService {
    List<DepartmentEntity> findAll();
    DepartmentEntity findById(Long id);
    DepartmentEntity save(DepartmentEntity department);
    void delete(Long id);
}


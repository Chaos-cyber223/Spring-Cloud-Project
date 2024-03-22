package org.example.search.gateway.emp_dep.repository;

import org.example.search.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}

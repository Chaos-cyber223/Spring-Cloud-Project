package org.example.search.gateway.emp_dep.repository;

import org.example.search.gateway.emp_dep.pojo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT e FROM EmployeeEntity e JOIN FETCH e.department")
    List<EmployeeEntity> findAllWithDepartment();
    @Query("SELECT e FROM EmployeeEntity e WHERE e.age > :age")
    List<EmployeeEntity> findByAgeGreaterThan(@Param("age") Integer age);

    @Modifying
    @Query("UPDATE EmployeeEntity e SET e.name = :name WHERE e.id = :id")
    void updateEmployeeName(@Param("id") Long id, @Param("name") String name);
}

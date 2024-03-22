package org.example.search.gateway.emp_dep.service.repository;

import org.example.search.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.example.search.gateway.emp_dep.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentRepoServiceImpl implements DepartmentRepoService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentRepoServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    @Transactional
    public DepartmentEntity save(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}


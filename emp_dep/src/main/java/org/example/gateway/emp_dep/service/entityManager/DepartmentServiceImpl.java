package org.example.gateway.emp_dep.service.entityManager;

import org.example.gateway.emp_dep.pojo.entity.DepartmentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DepartmentEntity> findAll() {
        return entityManager.createQuery("SELECT d FROM DepartmentEntity d", DepartmentEntity.class).getResultList();
    }

    @Override
    public DepartmentEntity findById(Long id) {
        DepartmentEntity department = entityManager.find(DepartmentEntity.class, id);
        if (department == null) {
            throw new RuntimeException("Department not found with id " + id);
        }
        return department;
    }

    @Override
    @Transactional
    public DepartmentEntity save(DepartmentEntity department) {
        if (department.getId() == null) {
            entityManager.persist(department);
            return department;
        } else {
            return entityManager.merge(department);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DepartmentEntity department = entityManager.find(DepartmentEntity.class, id);
        if (department != null) {
            entityManager.remove(department);
        }
    }

    @Override
    @Transactional
    public Long update(Long id, DepartmentEntity department) {
        DepartmentEntity existingDepartment = entityManager.find(DepartmentEntity.class, id);
        if (existingDepartment == null) {
            throw new EntityNotFoundException("Department not found with id " + id);
        }
        existingDepartment.setName(department.getName());
        entityManager.merge(existingDepartment);
        return existingDepartment.getId();
    }
}

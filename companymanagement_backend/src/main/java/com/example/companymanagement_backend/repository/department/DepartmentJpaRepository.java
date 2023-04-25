package com.example.companymanagement_backend.repository.department;

import com.example.companymanagement_backend.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Department Repository
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : Department Repository by Spring Data Jpa
 */
public interface DepartmentJpaRepository extends JpaRepository<Department, Long>, DepartmentJpaRepositoryCustom {
}

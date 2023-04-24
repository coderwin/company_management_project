package com.example.companymanagement_backend.repository.employee;

import com.example.companymanagement_backend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Employee Repository
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : Employee Repository by Spring Data Jpa
 */
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>, EmployeeJpaRepositoryCustom {

}

package com.example.companymanagement_backend.repository.employee;

import com.example.companymanagement_backend.domain.Employee;

import java.util.Optional;

/**
 * Employee Repository by using queryDsl
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : querydsl이용한 employeeRepository 모음
 */
public interface EmployeeJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.04.24
     * updated by writer :
     * update :
     * description : 직원 상세정보 찾기
     *               id : 직원 아이디
     */
    Optional<Employee> findDetailInfoById(Long id);
}
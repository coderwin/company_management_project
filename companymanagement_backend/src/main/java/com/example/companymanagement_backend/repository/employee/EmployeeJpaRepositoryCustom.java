package com.example.companymanagement_backend.repository.employee;

import com.example.companymanagement_backend.domain.Employee;

import java.util.List;
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

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer : 이호진
     * update : 2023.04.27
     * description : 모든 직원 정보 불러오기
     *
     * update : where 추가
     *          - 부서별 직원 찾기
     */
    List<Employee> findAllInfo(EmployeeSearchCond employeeSearchCond);
}

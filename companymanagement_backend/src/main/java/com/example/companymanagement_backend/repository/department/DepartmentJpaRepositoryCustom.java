package com.example.companymanagement_backend.repository.department;

import com.example.companymanagement_backend.domain.Department;

import java.util.Optional;

/**
 * Department Repository by using queryDsl
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : querydsl이용한 DepartmentRepository 모음
 */
public interface DepartmentJpaRepositoryCustom {

    /**
     * writer : 이호진
     * init : 2023.04.24
     * updated by writer :
     * update :
     * description : 부서 및 위치 정보 불러오기
     *               id : 직원 아이디
     */
    Optional<Department> findDepartmentAndLocationById(Long id);
}

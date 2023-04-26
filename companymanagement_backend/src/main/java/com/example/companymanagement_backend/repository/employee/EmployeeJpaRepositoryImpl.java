package com.example.companymanagement_backend.repository.employee;

import com.example.companymanagement_backend.domain.Employee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static com.example.companymanagement_backend.domain.QCountry.country;
import static com.example.companymanagement_backend.domain.QDepartment.department;
import static com.example.companymanagement_backend.domain.QEmployee.employee;
import static com.example.companymanagement_backend.domain.QJob.job;
import static com.example.companymanagement_backend.domain.QLocation.location;
import static com.example.companymanagement_backend.domain.QRegion.region;

/**
 * EmployeeJpaRepositoryCustom 구현
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : EmployeeJpaRepository 구현 모음 + 최적화 사용(fetch)
 */
@RequiredArgsConstructor
@Slf4j
public class EmployeeJpaRepositoryImpl implements EmployeeJpaRepositoryCustom {

    private final JPAQueryFactory query;// queryDsl 사용 위한 필드

    /**
     * writer : 이호진
     * init : 2023.04.24
     * updated by writer :
     * update :
     * description : 직원 상세정보 찾기
     *               id : 직원 아이디
     */
    @Override
    public Optional<Employee> findDetailInfoById(Long id) {
        // Employee 불러오기
        Employee findEmployee = query
                .select(employee)
                .from(employee)
                .join(employee.department, department).fetchJoin()
                .join(department.location, location).fetchJoin()
                .join(location.country, country).fetchJoin()
                .join(country.region, region).fetchJoin()
                .join(employee.job, job).fetchJoin()
                .where(employee.id.eq(id))
                .fetchOne();
        // Optional을 return
        return Optional.ofNullable(findEmployee);
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 모든 직원 정보 불러오기
     */
    @Override
    public List<Employee> findAllInfo() {
        // Employee 불러오기
        List<Employee> result = query
                .select(employee)
                .from(employee)
                .join(employee.department, department).fetchJoin()
                .fetch();

        return result;
    }

}

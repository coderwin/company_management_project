package com.example.companymanagement_backend.repository.employee;

import com.example.companymanagement_backend.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : Employee Repository by jpa Test
 */
@SpringBootTest
@Transactional
@Slf4j
public class EmployeeJpaRepositoryTest {

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;
    @PersistenceContext
    EntityManager em;

    Employee employee1;// 테스트 직원1

    // create initial data
    @BeforeEach
    public void init() {
        Long managerId = 1L;
        Date hireDate = Date.valueOf("2020-02-12");
        BigDecimal salary = new BigDecimal("1500");
        BigDecimal commissionPct = new BigDecimal("0.5");
        BigDecimal minSalary = new BigDecimal("1000");
        BigDecimal maxSalary = new BigDecimal("2000");

        // Region 생성
        Region region = Region.createRegion("ok");
        // Coountry 생성
        Country country = Country.createCountry("korea", region);
        // Location 생성
        Location location = Location.createLocation("c", "c", "city", "h", country);
        // Department 생성
        Department department = Department.createDepartment("backend", managerId, location);
        // Job 생성
        Job job = Job.createJob("사원", minSalary, maxSalary);
        // Employee 생성
        employee1 = Employee.createEmployee(
                "a",
                "ab",
                "a@a.a",
                "01012341234",
                hireDate,
                salary,
                commissionPct,
                managerId,
                department,
                job
                );

        // employee 저장
        em.persist(employee1);
    }

    @Test
    @DisplayName(value = "직원 상세정보 DB에서 불러오기 성공 테스트 ")
    @Rollback(value = false)
    void SucceedToGetEmployeeDetailInfo() {
        // given // when
        Optional<Employee> findEmployee = employeeJpaRepository.findDetailInfoById(employee1.getId());

        // then
        assertThat(findEmployee.orElse(null)).isEqualTo(employee1);
    }

    @Test
    @DisplayName(value = "직원 상세정보 DB에서 불러오기 실패 테스트 ")
    @Rollback(value = false)
    void failTogetEmployeeDetailInfo() {
        // given // when
        Optional<Employee> findEmployee = employeeJpaRepository.findDetailInfoById(employee1.getId() + 1L);

        // then
        assertThat(findEmployee.orElse(null)).isNull();
    }
}

package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 직원 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 직원 정보를 정의한다.
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "employees")
public class Employee {

    @Id
    @NotNull
    @Column(name = "employee_id")
    private Long id;// 직원 아이디
    @Column(length = 20)
    private String firstName;// 성
    @NotNull
    @Column(length = 25)
    private String lastName;// 이름
    @NotNull
    @Column(length = 25)
    private String email;// 이메일
    @Column(length = 25)
    private String PhoneNumber;// 휴대폰번호
    @NotNull
    private Date hireDate;// 입사날짜
    private BigDecimal salary;// 급여
    private BigDecimal commissionPct;// 수수료 퍼센트?
    private Long manager_id;// 관리번호

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;// 부서
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_id")
    private Job job;// 직급

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private JobHistory jobHistory;// 직원 직급 정보
}

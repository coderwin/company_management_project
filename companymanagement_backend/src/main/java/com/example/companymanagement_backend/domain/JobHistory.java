package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * 직원 이력 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer : 이호진
 * update : 2023.04.25
 * description : 직원 이력 정보를 정의한다.
 *
 * comment :
 *
 * update : employee와의 연관관계를 변경
 *          OneToOne -> ManyToOne
 */
@Entity
@Getter
public class JobHistory {

    @Id
    @NotNull
    private Date startDate;// 해당 직급 시작 날짜
    @NotNull
    private Date endDate;// 해당 직급 마지막 날짜

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_id")
    private Job job;// 직급
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;// 부서

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    private Employee employee;// 직원

    // ** setter ** //

    // ** 연관관계 메서드 ** //
    public void addEmployee(Employee employee) {
        employee.getJobHistoryList().add(this);
        this.employee = employee;
    }
}

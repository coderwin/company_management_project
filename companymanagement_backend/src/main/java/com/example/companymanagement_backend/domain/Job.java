package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 직급 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 직급 정보를 정의한다.
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "jobs")
public class Job {

    @Id
    @NotNull
    @Column(name = "job_id", length = 10)
    private String id;// 직급 아이디
    @Column(name = "job_title", length = 35)
    private String title;// 직급명
    private BigDecimal minSalary;// 최소 급여
    private BigDecimal maxSalary;// 최대 급여

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "job")
    private JobHistory jobHistory;// 직원 직급 정보
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "job")
    private Employee employee;// 직원 정보
}
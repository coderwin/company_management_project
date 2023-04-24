package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 부서 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 부서 정보를 정의한다.
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "departments")
public class Department {

    @Id
    @NotNull
    @Column(name = "department_id")
    private Long id;// 부서 아이디
    @NotNull
    @Column(name = "department_name", length = 30)
    private String name;// 부서명
    private Long managerId;// 관리 아이디
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id")
    private Location location;// 위치

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private JobHistory jobHistory;// 직원 직급 정보
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private Employee employee;// 직원 정보

    // ** setter ** //

    public void addName(String name) {
        this.name = name;
    }

    public void addManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void addJobHistory(JobHistory jobHistory) {
        this.jobHistory = jobHistory;
    }

    public void addEmployee(Employee employee) {
        this.employee = employee;
    }

    // ** 연관관계 메서드 ** //

    public void addLocation(Location location) {
        location.addDepartment(this);
        this.location = location;
    }


    // ** 생성 메서드 ** //

    public static Department createDepartment(String name, Long managerId, Location location) {

        Department department = new Department();

        department.addName(name);
        department.addManagerId(managerId);
        department.addLocation(location);

        return department;
    }
}

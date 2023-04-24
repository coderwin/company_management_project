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
    private String phoneNumber;// 휴대폰번호
    @NotNull
    private Date hireDate;// 입사날짜
    @NotNull
    @Column(precision = 8, scale = 2)
    private BigDecimal salary;// 급여
    @Column(precision = 2, scale = 2)
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

    // ** setter ** //
    public void addFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addEmail(String email) {
        this.email = email;
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void addSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void addCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public void addManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }

    public void addJobHistory(JobHistory jobHistory) {
        this.jobHistory = jobHistory;
    }

    // ** 연관관계 메서드 ** //

    public void addDepartment(Department department) {
        department.addEmployee(this);
        this.department = department;
    }

    public void addJob(Job job) {
        job.addEmployee(this);
        this.job = job;
    }

    // ** 생성 메서드 ** //

    public static Employee createEmployee(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            Date hireDate,
            BigDecimal salary,
            BigDecimal commissionPct,
            Long manager_id,
            Department department,
            Job job) {

        Employee employee = new Employee();

        employee.addFirstName(firstName);
        employee.addLastName(lastName);
        employee.addEmail(email);
        employee.addPhoneNumber(phoneNumber);
        employee.addHireDate(hireDate);
        employee.addSalary(salary);
        employee.addCommissionPct(commissionPct);
        employee.addManager_id(manager_id);
        employee.addDepartment(department);
        employee.addJob(job);

        return employee;
    }
}

package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 직원 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer : 이호진
 * update : 2023.04.25
 * description : 직원 정보를 정의한다.
 *
 * comment :
 *
 * update : jobHistory와의 연관관계를 변경
 *          OneToOne -> OneToMany
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
    private Long managerId;// 관리번호

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;// 부서
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_id")
    private Job job;// 직급

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private List<JobHistory> jobHistoryList = new ArrayList<>();// 직원 이력 정보 모음

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

    public void addManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void addJob(Job job) {
        this.job = job;
    }


    // ** 연관관계 메서드 ** //

    public void addDepartment(Department department) {
        department.getEmployeeList().add(this);
        this.department = department;
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
            Long managerId,
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
        employee.addManagerId(managerId);
        employee.addDepartment(department);
        employee.addJob(job);

        return employee;
    }

    // ** 비즈니스 로직 ** //
    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 특정 부서의 급여를 특정 비율로 인상하기
     *               increacedPct : 인상 비율
     *
     * comment : 소수점 두 자리까지만 나오게 하는 방법 생각해보기
     */
    public void increaseSalary(Integer increasedPct) {

        double increasedRate = searchIncreasedRate(increasedPct);// 인상된 비율
        // BigDecimal로 변환
        BigDecimal increasedRateBigDecimal = BigDecimal.valueOf(increasedRate);
        // 인상된 비율로 인상된 봉급 찾기
        BigDecimal increasedMoney = salary.multiply(increasedRateBigDecimal);
        // 인상된 금액 더하기
        salary = salary.add(increasedMoney);
    }

    private double searchIncreasedRate(Integer increasedPct) {
        // %를 소수점으로 변경
        double increasedRate = increasedPct / 100.0;

        return increasedRate;
    }
}

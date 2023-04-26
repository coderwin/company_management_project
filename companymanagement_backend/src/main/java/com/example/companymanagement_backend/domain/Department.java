package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList = new ArrayList<>();// 직원 정보

    // ** setter ** //

    public void addName(String name) {
        this.name = name;
    }
    public void addManagerId(Long managerId) {
        this.managerId = managerId;
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

    // ** 비즈니스 로직 ** //
    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 특정 부서의 급여를 특정 비율로 인상하기
     *               increacedPct : 인상 비율
     */
    public void increaseSalary(Integer increasedPct) {
        // 직원들의 연봉을 올리기
        employeeList.forEach((e) -> e.increaseSalary(increasedPct));
    }
}

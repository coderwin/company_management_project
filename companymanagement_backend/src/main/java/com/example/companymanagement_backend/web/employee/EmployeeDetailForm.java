package com.example.companymanagement_backend.web.employee;

import com.example.companymanagement_backend.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 직원 정보 상세보기 dto
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 직원의 상세정보를 담아둔다.
 */
@Getter @Setter
@AllArgsConstructor
public class EmployeeDetailForm {

    private Long employeeId;// 직원 아이디
    private String jobId;// 직급 아이디
    private Long managerId;// 메니저? 아이디
    private Long departmentId;// 부서 아이디
    private Long locationId;// 위치 아이디
    private String countryId;// 나라 아이디
    private String firstName;// 성
    private String lastName;// 이름
    private BigDecimal salary;// 급여
    private BigDecimal commissionPct;// 급여 퍼센트?
    private String departmentName;// 부서명
    private String jobTitle;//직급명
    private String city;// 도시명
    private String stateProvince;// 주명
    private String countryName;// 나라명
    private String regionName;// 지역명

    // ** 생성 메서드 ** //

    public static EmployeeDetailForm createEmployeeDetailForm(Employee employee) {

        return new EmployeeDetailForm(
                employee.getId(),
                employee.getJob().getId(),
                employee.getManagerId(),
                employee.getDepartment().getId(),
                employee.getDepartment().getLocation().getId(),
                employee.getDepartment().getLocation().getCountry().getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getCommissionPct(),
                employee.getDepartment().getName(),
                employee.getJob().getTitle(),
                employee.getDepartment().getLocation().getCity(),
                employee.getDepartment().getLocation().getStateProvince(),
                employee.getDepartment().getLocation().getCountry().getName(),
                employee.getDepartment().getLocation().getCountry().getRegion().getName()
        );
    }
}

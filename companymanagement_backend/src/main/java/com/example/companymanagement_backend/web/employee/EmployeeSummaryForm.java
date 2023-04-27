package com.example.companymanagement_backend.web.employee;

import com.example.companymanagement_backend.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 직원 요약 정보 dto
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 직원의 요약정보를 담아둔다.
 */
@Getter @Setter
@AllArgsConstructor
public class EmployeeSummaryForm {

    private Long id;// 직원 아이디
    private String firstName;// 성
    private String lastName;// 이름
    private String email;// 이메일
    private String departmentName;// 부서명

    // ** 생성 메서드 ** //
    public static EmployeeSummaryForm createEmployeeSummaryForm(Employee employee) {

        return new EmployeeSummaryForm(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getName()
        );
    }
}

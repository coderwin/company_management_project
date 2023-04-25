package com.example.companymanagement_backend.web.employee;

import com.example.companymanagement_backend.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 직원 이력 상세보기 dto 1
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 직원의 이력 상세정보 1 를 담아둔다.
 */
@Getter @Setter
@AllArgsConstructor
public class EmployeeJobHistoriesFrom {

    private Long id;// 직원 아이디
    private String firstName;// 성
    private String lastName;// 이름
    private List<JobHistoryForm> jobHistoryFormList;// 이력 모음 form

    // ** 생성 메서드 ** //

    public static EmployeeJobHistoriesFrom createEmployeeJSummaryForm(Employee employee) {

        return new EmployeeJobHistoriesFrom(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobHistoryList()
                        .stream()
                        .map((jh) -> JobHistoryForm.createJobHistoryFrom(jh))
                        .collect(Collectors.toList())
        );
    }
}

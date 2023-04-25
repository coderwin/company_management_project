package com.example.companymanagement_backend.web.employee;

import com.example.companymanagement_backend.domain.JobHistory;
import com.example.companymanagement_backend.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 직원 이력 상세보기 dto 2
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 직원의 이력 상세정보를 담아둔다.
 */
@Getter
@Setter
@AllArgsConstructor
public class JobHistoryForm {

    private Date startDate;// 해당 직급 시작 날짜
    private Date endDate;// 해당 직급 마지막 날짜
    private String jobTitle;// 직급명
    private BigDecimal minSalary;// 최소 급여
    private BigDecimal maxSalary;// 최대 급여
    private String departmentName;// 부서명

    // ** 생성 메서드 ** //
    public static JobHistoryForm createJobHistoryFrom(JobHistory jobHistory) {

        return new JobHistoryForm(
                jobHistory.getStartDate(),
                jobHistory.getEndDate(),
                jobHistory.getJob().getTitle(),
                jobHistory.getJob().getMinSalary(),
                jobHistory.getJob().getMaxSalary(),
                jobHistory.getDepartment().getName()
        );
    }
}

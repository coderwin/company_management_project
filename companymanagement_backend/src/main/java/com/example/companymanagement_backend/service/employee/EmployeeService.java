package com.example.companymanagement_backend.service.employee;

import com.example.companymanagement_backend.repository.employee.EmployeeSearchCond;
import com.example.companymanagement_backend.web.employee.EmployeeDetailForm;
import com.example.companymanagement_backend.web.employee.EmployeeJobHistoriesFrom;
import com.example.companymanagement_backend.web.employee.EmployeeSummaryForm;

import java.util.List;

/**
 * Employee Service
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : Employee Service 메소드 모음
 */
public interface EmployeeService {

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 현재 직원 정보 상세보기
     */
    EmployeeDetailForm watchDetail(Long id);

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 직원의 이력 정보 보기
     */
    EmployeeJobHistoriesFrom watchJobHistory(Long id);

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer : 이호진
     * update : 2023.04.27
     * description : 모든 직원 불러오기
     *
     * update : 검색 조건 form 추가
     */
    List<EmployeeSummaryForm> selectList(EmployeeSearchCond employeeSearchCond);
}

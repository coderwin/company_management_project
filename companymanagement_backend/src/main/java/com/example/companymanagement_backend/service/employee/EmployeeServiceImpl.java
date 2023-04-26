package com.example.companymanagement_backend.service.employee;

import com.example.companymanagement_backend.domain.Employee;
import com.example.companymanagement_backend.repository.employee.EmployeeJpaRepository;
import com.example.companymanagement_backend.web.employee.EmployeeDetailForm;
import com.example.companymanagement_backend.web.employee.EmployeeJobHistoriesFrom;
import com.example.companymanagement_backend.web.employee.EmployeeSummaryForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeJpaRepository employeeJpaRepository;

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 현재 직원 정보 상세보기
     */
    @Override
    public EmployeeDetailForm watchDetail(Long id) {
        // 직원 정보 불러오기
        Employee findEmployee = employeeJpaRepository.findDetailInfoById(id).orElseThrow();
        // EmployeeDetailForm으로 변경
        return EmployeeDetailForm.createEmployeeDetailForm(findEmployee);
    }

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 직원의 이력 정보 보기
     */
    @Override
    public EmployeeJobHistoriesFrom watchJobHistory(Long id) {
        // 직원의 이력 정보 List 가져오기
        Employee findEmployee = employeeJpaRepository.findById(id).orElseThrow();
        // EmployeeJobHistoriesFrom으로 변경하기
        EmployeeJobHistoriesFrom employeeJobHistoriesFrom = EmployeeJobHistoriesFrom.createEmployeeJSummaryForm(findEmployee);

        return employeeJobHistoriesFrom;
    }

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer : 이호진
     * update : 2023.04.26
     * description : 모든 직원 불러오기
     *
     * update : findAll -> findAllInfo 변경
     *          - Data JPA 메서드 사용하니 jooHistory에서 에러 발생
     */
    @Override
    public List<EmployeeSummaryForm> selectList() {
        // 모든 직원 불러오기
        List<Employee> employees = employeeJpaRepository.findAllInfo();
        // EmployeeSummaryForm으로 변경하기
        List<EmployeeSummaryForm> employeeSummaryFormList = employees
                .stream()
                .map(e -> EmployeeSummaryForm.createEmployeeSummaryForm(e))
                .collect(toList());

        return employeeSummaryFormList;
    }
}

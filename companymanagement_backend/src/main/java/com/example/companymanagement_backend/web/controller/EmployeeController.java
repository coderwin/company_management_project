package com.example.companymanagement_backend.web.controller;

import com.example.companymanagement_backend.service.employee.EmployeeService;
import com.example.companymanagement_backend.web.employee.EmployeeDetailForm;
import com.example.companymanagement_backend.web.employee.EmployeeJobHistoriesFrom;
import com.example.companymanagement_backend.web.employee.EmployeeSummaryForm;
import com.example.companymanagement_backend.web.resultBox.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Employee Controller
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : Employee RestController 메소드 모음
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 직원 정보 상세보기
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "직원 정보 상세보기")
    public Result<EmployeeDetailForm> watchDetail(@PathVariable Long id) {
        log.info("watchDetail employee id : {}", id);
        // 직원 정보 상세보기 불러오기
        EmployeeDetailForm form = employeeService.watchDetail(id);
        // Result 생성
        Result<EmployeeDetailForm> result = new Result<>(form);

        return result;
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 직원의 이력 정보 보기
     */
    @GetMapping("/{id}/jobhistories")
    @ApiOperation(value = "직원 이력 정보 보기")
    public Result<EmployeeJobHistoriesFrom> watchJobHistory(@PathVariable Long id) {
        log.info("watchJobHistory employee id : {}", id);
        // 직원 이력 정보 불러오기
        EmployeeJobHistoriesFrom form = employeeService.watchJobHistory(id);
        // Result 생성
        Result<EmployeeJobHistoriesFrom> result = new Result<>(form);

        return result;
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 모든 직원 불러오기
     */
    @GetMapping
    @ApiOperation(value = "직원 목록")
    public Result<List<EmployeeSummaryForm>> selectList() {
        log.info("call all employees");
        // 모든 직원 불러오기
        List<EmployeeSummaryForm> listForm = employeeService.selectList();
        // Result 생성
        Result<List<EmployeeSummaryForm>> result = new Result<>(listForm);

        return result;
    }

}

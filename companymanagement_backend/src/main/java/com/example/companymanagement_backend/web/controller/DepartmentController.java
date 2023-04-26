package com.example.companymanagement_backend.web.controller;

import com.example.companymanagement_backend.exception.ValidatedErrorsMsg;
import com.example.companymanagement_backend.service.department.DepartmentService;
import com.example.companymanagement_backend.web.department.DepartmentDetailForm;
import com.example.companymanagement_backend.web.department.DepartmentIncreaseSalaryFrom;
import com.example.companymanagement_backend.web.department.DepartmentListForm;
import com.example.companymanagement_backend.web.resultBox.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department Controller
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : Department RestController 메소드 모음
 */
@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 부서 및 위치 정보 불러오기
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "부서 및 위치 정보 불러오기")
    public Result<DepartmentDetailForm> watchDetail(@PathVariable Long id) {
        // 부서 정보 불러오기
        DepartmentDetailForm form = departmentService.watchDetail(id);
        // Result 생성
        Result<DepartmentDetailForm> result = new Result<>(form);

        return result;
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 모든 부서 불러오기
     */
    @GetMapping
    @ApiOperation(value = "부서 목록")
    public Result<List<DepartmentListForm>> selectList() {
        // 모든 부서 불러오기
        List<DepartmentListForm> listForm = departmentService.selectList();
        // Result 생성
        Result<List<DepartmentListForm>> result = new Result<>(listForm);

        return result;
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 특정 부서의 급여를 특정 비율로 인상하기
     *               id : 부서 아이디
     *               increacedPct : 인상 비율
     */
    @PatchMapping
    @ApiOperation(value = "특정 부서의 급여를 특정 비율로 인상하기")
    public ResponseEntity<?> increaseSalary(@RequestBody @Validated DepartmentIncreaseSalaryFrom form,
                                            BindingResult bindingResult) {
        /// 데이터 검증하기
        // bindngResult에 에러 있는지 확인
        if(bindingResult.hasErrors()) {
            log.info("department increaseSalary error : {}", bindingResult);

            // 클라이언트에게 에러 메시지 보내기
            return ValidatedErrorsMsg.makeValidatedErrorsContents(bindingResult);
        }
        /// 검증 통과
        // 급여 인상하기
        String departmentName = departmentService.increaseSalary(form.getId(), form.getIncreasedPct());
        // 급여 인상 완료
        String successMsg = "'" + departmentName + "'부서 급여 인상 완료";
        // responseEntity body 생성
        Result<String> body = new Result<>(successMsg);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}

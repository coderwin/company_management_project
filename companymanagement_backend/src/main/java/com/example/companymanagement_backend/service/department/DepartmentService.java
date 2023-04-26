package com.example.companymanagement_backend.service.department;

import com.example.companymanagement_backend.web.department.DepartmentDetailForm;
import com.example.companymanagement_backend.web.department.DepartmentListForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Department Service
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : Department Service 메소드 모음
 */
public interface DepartmentService {

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 부서 및 위치 정보 불러오기
     *               id : 부서 아이디
     */
    DepartmentDetailForm watchDetail(Long id);

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 모든 부서 불러오기
     */
    List<DepartmentListForm> selectList();

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 특정 부서의 급여를 특정 비율로 인상하기
     *               id : 부서 아이디
     *               increacedPct : 인상 비율
     */
    @Transactional(readOnly = false)
    void increaseSalary(Long id, Integer increasedPct);
}

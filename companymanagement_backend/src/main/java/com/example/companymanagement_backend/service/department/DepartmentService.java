package com.example.companymanagement_backend.service.department;

import com.example.companymanagement_backend.web.department.DepartmentDetailForm;
import com.example.companymanagement_backend.web.department.DepartmentListForm;

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
}

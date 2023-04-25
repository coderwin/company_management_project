package com.example.companymanagement_backend.web.department;

import com.example.companymanagement_backend.domain.Department;
import com.example.companymanagement_backend.web.location.LocationDetailForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 부서 목록 dto
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 부서의 상세정보를 담아둔다.
 */
@Getter
@Setter
@AllArgsConstructor
public class DepartmentDetailForm {

    private Long id;// 부서 아이디
    private String name;// 부서명
    private Long managerId;// 관리 아이디
    private LocationDetailForm locationDetailForm;// 위치 상세정보 dto

    // ** 생성 메서드 ** //
    public static DepartmentDetailForm create(Department department) {

        return new DepartmentDetailForm(
                department.getId(),
                department.getName(),
                department.getManagerId(),
                LocationDetailForm.create(department.getLocation())
        );
    }

}

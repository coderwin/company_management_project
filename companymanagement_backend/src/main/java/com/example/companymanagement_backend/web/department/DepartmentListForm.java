package com.example.companymanagement_backend.web.department;

import com.example.companymanagement_backend.domain.Department;
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
@Getter @Setter
@AllArgsConstructor
public class DepartmentListForm {

    private Long id;// 부서 아이디
    private String name;// 부서명
    private Long managerId;// 관리 아이디

    // ** 생성 메서드 ** //
    public static DepartmentListForm create(Department department) {
        return new DepartmentListForm(
                department.getId(),
                department.getName(),
                department.getManagerId()
        );
    }
}

package com.example.companymanagement_backend.web.department;

import lombok.*;

import javax.validation.constraints.Positive;

/**
 * Department IncreaseSalary dto
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : 클라이언트에게서 받은 부서 및 급여 인상 비율 정보를 담아둔다.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentIncreaseSalaryFrom {

    private Long id;// 부서 아이디
    @Positive(message = "양수만 입력 가능합니다.")
    private Integer increasedPct;// 인상 비율
}

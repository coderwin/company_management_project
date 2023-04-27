package com.example.companymanagement_backend.repository.employee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Employee findAll for condition dto
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : Employee finaAll에 사용되는 where 절의 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeSearchCond {

    private Long departmentId;// 부서 아이디
}

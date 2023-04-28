package com.example.companymanagement_backend.web.openapi.finedustinfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FineDustInfo findAll for condition dto
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : FineDustInfo finaAll에 사용되는 조건 데이터 모음
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FineDustInfoCond {

    private String numOfRows;// 한 페이지 결과 수
    private String pageNo;// 페이지번호
    private String year;// 측정 연도
    private String itemCode; // (empty), PM10, PM25
}

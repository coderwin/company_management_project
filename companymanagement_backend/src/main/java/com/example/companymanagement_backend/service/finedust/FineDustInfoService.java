package com.example.companymanagement_backend.service.finedust;

import com.example.companymanagement_backend.web.openapi.finedustinfo.FineDustInfoCond;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * FineDustInfo Service
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : FineDustInfo Service 메소드 모음
 *               -> 미세먼지 open api 요청 service
 */
public interface FineDustInfoService {

    /**
     * writer : 이호진
     * init : 2023.04.28
     * updated by writer :
     * update :
     * description : 미세먼지 정보 데이터 불러오기
     */
    JSONObject selectData(FineDustInfoCond fineDustInfoCond) throws IOException, ParseException;
}

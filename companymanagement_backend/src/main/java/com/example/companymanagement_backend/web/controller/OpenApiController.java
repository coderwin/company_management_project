package com.example.companymanagement_backend.web.controller;

import com.example.companymanagement_backend.service.finedust.FineDustInfoService;
import com.example.companymanagement_backend.web.openapi.finedustinfo.FineDustInfoCond;
import com.example.companymanagement_backend.web.resultBox.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Open API Controller
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : Open API RestController 메소드 모음
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {

    private final FineDustInfoService fineDustInfoService;// 미세먼지 open api service

    /**
     * writer : 이호진
     * init : 2023.04.28
     * updated by writer :
     * update :
     * description : 미세먼지 정보 조회
     */
    @GetMapping("/finedustinfo")
    @ApiOperation(value = "미세먼지 정보 조회")
    public Result<JSONObject> getFineDustInfoData(FineDustInfoCond fineDustInfoCond) throws IOException, ParseException {
        log.info("URL /finedustinfo 요청");

        // 미세먼지 데이터 불러오기
        JSONObject datas = fineDustInfoService.selectData(fineDustInfoCond);
        // Result 생성
        Result result = new Result<>(datas);

        return result;
    }


}

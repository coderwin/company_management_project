package com.example.companymanagement_backend.service.finedust;

import com.example.companymanagement_backend.util.openapi.FineDustInfo;
import com.example.companymanagement_backend.web.openapi.finedustinfo.FineDustInfoCond;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * FineDustInfo Service
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : FineDustInfo Service 구현 메소드 모음
 *               -> 미세먼지 open api 요청 service
 */
@Service
@Slf4j
public class FineDustInfoServiceImpl implements FineDustInfoService {

    /**
     * writer : 이호진
     * init : 2023.04.28
     * updated by writer :
     * update :
     * description : 미세먼지 정보 데이터 불러오기
     */
    @Override
    public JSONObject selectData(FineDustInfoCond fineDustInfoCond) throws IOException, ParseException {
        // FineDustInfo 생성
        FineDustInfo fineDustInfo = FineDustInfo.createFineDustInfo(fineDustInfoCond);
        // FineDustInfo 불러오기
        String datas = fineDustInfo.getFineDustInfo();
        /// json 객체로 반환하기
        JSONObject result = createJSONObject(datas);

        return result;
    }

    // String을 JSONObject로 바꾸기
    private JSONObject createJSONObject(String datas) throws ParseException {
        // json parsing 객체 생성
        JSONParser jsonParser = new JSONParser();
        // String을 json 형태로 JSONObject 객체에 저장
        JSONObject jsonObject = (JSONObject) jsonParser.parse(datas);

        return jsonObject;
    }
}

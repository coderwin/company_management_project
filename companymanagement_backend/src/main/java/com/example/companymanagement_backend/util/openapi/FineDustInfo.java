package com.example.companymanagement_backend.util.openapi;

import com.example.companymanagement_backend.web.openapi.finedustinfo.FineDustInfoCond;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * FineDustInfo util
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : FineDustInfo util
 *               -> 미세먼지 open api 요청 코드 작성
 */
@Getter
@Slf4j
public class FineDustInfo {

    private final String KEY = "";
    private final String RETURN_TYPE = "json";// 데이터표출방식 /*xml 또는 json*/
    private String numOfRows;// 한 페이지 결과 수
    private String pageNo;// 페이지번호
    private String year;// 측정 연도
    private String itemCode; // (empty), PM10, PM25

    // ** setter ** //

    public void addNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void addPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void addYear(String year) {
        this.year = year;
    }

    public void addItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    // ** 생성 메서드  ** //

    public static FineDustInfo createFineDustInfo(FineDustInfoCond fineDustInfoCond) {

        FineDustInfo fineDustInfo = new FineDustInfo();

        fineDustInfo.addNumOfRows(fineDustInfoCond.getNumOfRows());
        fineDustInfo.addPageNo(fineDustInfoCond.getPageNo());
        fineDustInfo.addYear(fineDustInfoCond.getYear());
        fineDustInfo.addItemCode(fineDustInfoCond.getItemCode());

        return fineDustInfo;
    }

    // ** 비즈니스 로직 ** //

    /**
     * writer : 이호진
     * init : 2023.04.28
     * updated by writer :
     * update :
     * description : 공공 데이터 서비스 사이트로부터 미세먼지 정보 요청하기
     */
    public String getFineDustInfo() throws IOException {
        // 요청 URL 만들기
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(RETURN_TYPE, "UTF-8")); /*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode(year, "UTF-8")); /*측정 연도*/
        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode(itemCode, "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/

        URL url = new URL(urlBuilder.toString());

        // api 요청 connection 만들기
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        // 요청 성공, 요청 실패 구분
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // 요청 데이터 StringBuilder에 담기
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        // 외부 통신 reader 끊기
        rd.close();
        // 외부 연결 connection 끊기
        conn.disconnect();

        // 요청 데이터 반환
        log.info(sb.toString());
        return sb.toString();
    }
}

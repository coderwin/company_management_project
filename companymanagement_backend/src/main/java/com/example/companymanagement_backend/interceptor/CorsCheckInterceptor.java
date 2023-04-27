package com.example.companymanagement_backend.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CorsCheck Interceptor
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : CorsCheck Interceptor 처리 로직 구현
 */
@Slf4j
public class CorsCheckInterceptor implements HandlerInterceptor {

    /**
     * writer : 이호진
     * init : 2023.04.27
     * updated by writer :
     * update :
     * description : cros처리 중 preflight 처리 체크
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 요청 uri 확인
        String requestURI = request.getRequestURI();
        log.info("CorsCheckInterceptor preHandle URI : {}", requestURI);

        // preflight는 OPTIONS임을 이용해 OPTIONS 모두 혀용하기
        if(HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        // 모든 요청 인터셉터 통과
        return true;
    }
}

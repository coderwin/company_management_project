package com.example.companymanagement_backend.config;

import com.example.companymanagement_backend.interceptor.CorsCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 사용자 정의 Interceptor 등록
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 사용자 정의 Interceptor 등록
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    /**
     * writer : 이호진
     * init : 2023.04.27
     * updated by writer :
     * update :
     * description : 인터셉터들을 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // CORS 검사 인터셉터 등록
        registry.addInterceptor(new CorsCheckInterceptor())
                .order(1)
                .addPathPatterns("/**");
    }

    /**
     * writer : 이호진
     * init : 2023.04.27
     * updated by writer :
     * update :
     * description : Access-Control-Allow-Origin 문제 해결위한 설정
     *
     * comment : CORS 정책과 session 확인을 위해
     *          > allowedOrigins 추가
     *          > allowCredentials 추가
     *          > allowedOriginPatterns 추가
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }
}

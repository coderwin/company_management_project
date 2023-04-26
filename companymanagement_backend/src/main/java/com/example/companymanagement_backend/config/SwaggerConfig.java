package com.example.companymanagement_backend.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 사용 위한 bean 생성
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : Swagger 사용 위한 bean 생성위한 config
 */
//@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    // 어떤 api인지 설명하기
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("company_management Rest API")
                .version("1.0.0")
                .description("company_management의 swagger API 입니다.")
                .build();
    }
}

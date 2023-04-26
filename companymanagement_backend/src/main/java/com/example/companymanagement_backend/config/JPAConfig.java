package com.example.companymanagement_backend.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * QueryDsl 사용 위한 bean 생성
 * writer : 이호진
 * init : 2023.04.26
 * updated by writer :
 * update :
 * description : QueryDsl 사용 위한 bean 생성위한 config
 */
@Configuration
public class JPAConfig {

    @PersistenceContext
    private EntityManager em;

    /**
     * JPA 사용 위한 bean 생성
     * writer : 이호진
     * init : 2023.01.16
     * updated by writer :
     * update :
     * description : queryDSL 사용할 수 있는 bean 생성
     */
    @Bean
    public JPAQueryFactory createJPAQueryFactory() {
        return new JPAQueryFactory(em);
    }








}

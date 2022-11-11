package com.jpa.datajpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManager;

/**
 * 設定QueryDSL的Bean放入ioc中
 */
@Configuration
public class jpaConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return  new JPAQueryFactory(entityManager);
    }
}

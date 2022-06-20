package com.kyobong.store.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {

	@PersistenceContext(name = "entityManager")
	private EntityManager em;
	
    @Bean(name="queryFactory")
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }
    
}

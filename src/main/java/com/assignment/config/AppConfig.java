package com.assignment.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


    @Configuration
    @EnableTransactionManagement
    @ComponentScans(value = { @ComponentScan("com.assignment.*")})
    @SpringBootApplication

    public class AppConfig extends SpringBootServletInitializer {
        @Bean(name="entityManager")
        public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
            LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
            factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");
            return factoryBean;
        }
        @Bean
        public JpaTransactionManager geJpaTransactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
            return transactionManager;
        }
        public static void main(String[] args) throws Exception {
            SpringApplication.run(AppConfig.class, args);
        }

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(AppConfig.class);
        }
    }


package com.github.chizzaru.config;

import com.github.chizzaru.CustomAppProperties;
import com.github.chizzaru.beans.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.github.chizzaru")
public class AppConfig {


    @Autowired
    private CustomAppProperties properties;


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriver());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean
    public Emp emp(){
        return new Emp();
    }
}

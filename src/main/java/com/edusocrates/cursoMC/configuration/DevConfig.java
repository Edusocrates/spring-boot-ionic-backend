package com.edusocrates.cursoMC.configuration;

import com.edusocrates.cursoMC.serivce.impl.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Autowired
    private DBServiceImpl dbService;

    @Bean
    public boolean instanteateDataBase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }
        dbService.instanteateDataBase();
        return true;
    }
}

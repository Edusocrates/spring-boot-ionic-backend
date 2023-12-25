package com.edusocrates.cursoMC.configuration;

import com.edusocrates.cursoMC.serivce.DBService;
import com.edusocrates.cursoMC.serivce.EmailService;
import com.edusocrates.cursoMC.serivce.impl.DBServiceImpl;
import com.edusocrates.cursoMC.serivce.impl.SmtpEmailService;
import com.edusocrates.cursoMC.serivce.impl.mock.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServiceImpl dbService;

    @Bean
    public boolean instanteateDataBase() throws ParseException {
        dbService.instanteateDataBase();
        return true;
    }


    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}

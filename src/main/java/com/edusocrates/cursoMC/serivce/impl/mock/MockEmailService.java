package com.edusocrates.cursoMC.serivce.impl.mock;

import com.edusocrates.cursoMC.serivce.impl.AbstractEmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEmailService extends AbstractEmailServiceImpl {


    private static final Logger logging = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message){
        logging.info("Simulando envio de email......");
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");

    }


}

package com.edusocrates.cursoMC.serivce.impl.mock;

import com.edusocrates.cursoMC.serivce.impl.AbstractEmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class MockEmailService extends AbstractEmailServiceImpl {


    private static final Logger logging = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void enviaEmail(SimpleMailMessage message){
        logging.info("Simulando envio de email......");
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");

    }

    @Override
    public void enviaHTMLEmail(MimeMessage message) {
        logging.info("Simulando envio de email HTML......");
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");
    }


}

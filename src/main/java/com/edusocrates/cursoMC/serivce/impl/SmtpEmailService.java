package com.edusocrates.cursoMC.serivce.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailServiceImpl {

    private static final Logger logging = LoggerFactory.getLogger(SmtpEmailService.class);



    @Autowired
    private MailSender mailSender;


    @Override
    public void sendEmail(SimpleMailMessage message){
        logging.info("Simulando envio de email......");
        mailSender.send(message);
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");

    }



}

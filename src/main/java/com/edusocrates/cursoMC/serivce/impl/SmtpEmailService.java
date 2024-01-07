package com.edusocrates.cursoMC.serivce.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailServiceImpl {

    private static final Logger logging = LoggerFactory.getLogger(SmtpEmailService.class);



    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void enviaEmail(SimpleMailMessage message){
        logging.info("Simulando envio de email......");
        mailSender.send(message);
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");

    }

    @Override
    public void enviaHTMLEmail(MimeMessage message) {
        logging.info("Simulando envio de email HTML......");
        javaMailSender.send(message);
        logging.info(message.toString());
        logging.info("EMAIL ENVIADO COM SUCESSO!");
    }


}

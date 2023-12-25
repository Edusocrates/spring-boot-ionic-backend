package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);


    void sendOrderConfirmationHTMLEmail(Pedido pedido);

    void sendHTMLEmail(MimeMessage message);

}

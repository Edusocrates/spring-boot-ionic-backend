package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);


}

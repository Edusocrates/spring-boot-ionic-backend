package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.model.Pedido;
import com.edusocrates.cursoMC.serivce.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public abstract class AbstractEmailServiceImpl implements EmailService {

    @Value("${default.recipient}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Codigo: "+ pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {

    }
}

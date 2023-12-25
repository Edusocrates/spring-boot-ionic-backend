package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.model.Pedido;
import com.edusocrates.cursoMC.serivce.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public abstract class AbstractEmailServiceImpl implements EmailService {

    @Value("${default.recipient}")
    private String sender;


    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    private JavaMailSender javaMailSender;


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

    @Override
    public void sendOrderConfirmationHTMLEmail(Pedido pedido) {
        try {
            MimeMessage mm  = prepareMimeMessageFromPedido(pedido);
            sendHTMLEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    private MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido Confirmado! Codigo: "+ pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido),true);
        return mimeMessage;
    }


    protected String htmlFromTemplatePedido(Pedido pedido){
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido",context);
    }


}

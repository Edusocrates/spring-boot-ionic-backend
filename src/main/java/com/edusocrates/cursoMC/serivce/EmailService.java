package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void enviaConfirmacaoPedidoEmail(Pedido pedido);

    void enviaEmail(SimpleMailMessage message);


    void enviaConfirmacaoPedidoHTMLEmail(Pedido pedido);

    void enviaHTMLEmail(MimeMessage message);

    void enviaNovaSenhaPorEmail(Cliente cliente,String novaSenha);

}

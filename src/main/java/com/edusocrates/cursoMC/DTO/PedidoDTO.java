package com.edusocrates.cursoMC.DTO;

import com.edusocrates.cursoMC.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PedidoDTO {

        private Integer id;
        private Date instante;
        private Pagamento pagamento;
        private Cliente cliente;
        private Endereco enderecoDeEntrega;
        private Set<ItemPedido> itens = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public PedidoDTO(Integer id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega, Set<ItemPedido> itens) {
        this.id = id;
        this.instante = instante;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.itens = itens;
    }
    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.instante = pedido.getInstante();
        this.pagamento = pedido.getPagamento();
        this.cliente = pedido.getCliente();
        this.enderecoDeEntrega = pedido.getEnderecoDeEntrega();
        this.itens = pedido.getItens();
    }

    public PedidoDTO() {
    }
}

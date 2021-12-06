package com.edusocrates.cursoMC.model;

import com.edusocrates.cursoMC.model.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "PAGAMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCodigo();
        this.pedido = pedido;
    }

    public Pagamento() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(getId(), pagamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
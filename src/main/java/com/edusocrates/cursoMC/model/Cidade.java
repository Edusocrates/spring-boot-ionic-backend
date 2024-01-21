package com.edusocrates.cursoMC.model;

import com.edusocrates.cursoMC.DTO.CidadeDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CIDADE")
public class Cidade implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(getId(), cidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static List<CidadeDTO> entitiesToDTOs(List<Cidade> cidades) {
        List<CidadeDTO> cidadeDTOs = new ArrayList<>();

        for (Cidade cidade : cidades) {
            cidadeDTOs.add(entityToDTO(cidade));
        }

        return cidadeDTOs;
    }
    private static CidadeDTO entityToDTO(Cidade cidade) {
        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setId(cidade.getId());
        cidadeDTO.setNome(cidade.getNome());
        cidadeDTO.setEstado(cidade.getEstado());


        return cidadeDTO;
    }
}

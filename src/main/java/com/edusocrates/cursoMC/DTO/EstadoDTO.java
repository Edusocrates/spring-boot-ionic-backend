package com.edusocrates.cursoMC.DTO;

import com.edusocrates.cursoMC.model.Cidade;

import java.util.ArrayList;
import java.util.List;


public class EstadoDTO {

    private Integer id;

    private String nome;

    private List<Cidade> cidades = new ArrayList<>();


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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public EstadoDTO() {
    }

    public EstadoDTO(Integer id, String nome, List<Cidade> cidades) {
        this.id = id;
        this.nome = nome;
        this.cidades = cidades;
    }
}

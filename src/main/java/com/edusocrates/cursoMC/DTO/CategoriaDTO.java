package com.edusocrates.cursoMC.DTO;

import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;

import java.util.ArrayList;
import java.util.List;


public class CategoriaDTO {

    private Integer id;
    private String nome;


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

    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public CategoriaDTO() {
    }
}

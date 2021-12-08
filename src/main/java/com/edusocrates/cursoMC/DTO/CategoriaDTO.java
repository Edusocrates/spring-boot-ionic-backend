package com.edusocrates.cursoMC.DTO;

import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;

import java.util.ArrayList;
import java.util.List;


public class CategoriaDTO {

    private Integer id;
    private String nome;
    private List<Produto> produtos = new ArrayList<>();

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO() {
    }
}

package com.edusocrates.cursoMC.DTO;

import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Integer id;

    private String nome;

    private Double preco;

    private List<Categoria> categorias = new ArrayList<>();

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public ProdutoDTO(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.categorias = produto.getCategorias();//verificar
    }

    public ProdutoDTO() {
    }
}

package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria getCategoriaById(Integer categoriaId);

    Categoria insertCategoria(Categoria categoria);

    Categoria updateCategoria(Integer id, Categoria requestCategoria);

    void deleteCategoria(Integer id);

    List<Categoria> getAllCategorias();
}

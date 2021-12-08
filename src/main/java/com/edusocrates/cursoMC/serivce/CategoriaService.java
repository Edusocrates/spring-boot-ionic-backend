package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.model.Categoria;

public interface CategoriaService {
    Categoria getCategoriaById(Integer categoriaId);

    Categoria insertCategoria(Categoria categoria);
}

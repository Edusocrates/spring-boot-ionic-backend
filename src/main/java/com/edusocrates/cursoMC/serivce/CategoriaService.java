package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaService {
    Categoria getCategoriaById(Integer categoriaId);

    CategoriaDTO insertCategoria(CategoriaDTO categoriaDTO);

    Categoria updateCategoria(Integer id, Categoria requestCategoria);

    void deleteCategoria(Integer id);

    List<Categoria> getAllCategorias();

     Page<Categoria> findAllWithPagenation(Integer page, Integer linesPerPage,
                                                 String orderBy, String direction);
}

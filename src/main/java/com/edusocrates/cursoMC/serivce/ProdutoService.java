package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.ProdutoDTO;
import com.edusocrates.cursoMC.model.Produto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdutoService {


    Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage,
                         String orderBy,String direction);

    ProdutoDTO getProdutoById(Integer id);
}

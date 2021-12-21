package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.ProdutoDTO;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;
import com.edusocrates.cursoMC.repository.CategoriaRepository;
import com.edusocrates.cursoMC.repository.ProdutoRepository;
import com.edusocrates.cursoMC.serivce.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage,
                                String orderBy,String direction){
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage,
                Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categoriaList = categoriaRepository.findAllById(ids);
        return repository.search(nome,categoriaList,pageRequest);
    }

    @Override
    public ProdutoDTO getProdutoById(Integer id) {
        Produto produto = findById(id);
        return new ProdutoDTO(produto);
    }

    private Produto findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ObjectNotFoundException("produto não encontrado! Id: "+id
                                +" Tipo: "+ Produto.class.getName()));
    }
}

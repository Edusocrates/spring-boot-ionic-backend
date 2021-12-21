package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.DTO.ProdutoDTO;
import com.edusocrates.cursoMC.controller.utils.URL;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;
import com.edusocrates.cursoMC.serivce.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProdutoById(@PathVariable Integer id){
        ProdutoDTO produto = service.getProdutoById(id);
        return ResponseEntity.ok(produto);
    }
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> getAllCategoriasWithPagination(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                                           @RequestParam(name = "lines",defaultValue = "24") Integer linesPerPage,
                                                                           @RequestParam(name = "order",defaultValue = "nome") String orderBy,
                                                                           @RequestParam(name = "direction",defaultValue = "ASC") String direction,
                                                                           @RequestParam(name = "nome",defaultValue = "") String nome,
                                                                           @RequestParam(name = "categorias",defaultValue = "") String categorias){
        String nomeDecoded = URL.decodeParam(nome);//caso nome venha com espaço, ele retira o codigo gerado pelo espaço
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> produtoList = service.search(nomeDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProdutoDTO> produtoDTOList = produtoList.map(produto -> new ProdutoDTO(produto));
        return ResponseEntity.ok(produtoDTOList);
    }
}

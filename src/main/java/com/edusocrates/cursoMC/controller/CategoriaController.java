package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.serivce.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;


    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Integer id){
        Categoria categoria = service.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias(){
        List<Categoria> categoriaList = service.getAllCategorias();
        List<CategoriaDTO> categoriaDTOList = categoriaList
                .stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOList);
    }

    @PostMapping
    public ResponseEntity<?> insertCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria = service.insertCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Integer id,
                                             @RequestBody Categoria requestCategoria){
        Categoria updatedCategoria = service.updateCategoria(id,requestCategoria);
        return ResponseEntity.ok(updatedCategoria);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id){
            service.deleteCategoria(id);
        return ResponseEntity.ok(null);
    }
}

package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.serivce.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
}

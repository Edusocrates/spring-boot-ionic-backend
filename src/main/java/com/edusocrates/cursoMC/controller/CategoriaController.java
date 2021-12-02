package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.model.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @GetMapping
    public List<Categoria> listar(){
        Categoria categoria = new Categoria(1,"informatica");
        Categoria categoria2 = new Categoria(2,"Escritorio");

        List<Categoria> list = new ArrayList<>();
        list.add(categoria);
        list.add(categoria2);
        return list;
    }
}

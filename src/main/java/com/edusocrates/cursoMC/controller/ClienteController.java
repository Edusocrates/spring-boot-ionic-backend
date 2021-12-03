package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;


    @GetMapping("{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
        Cliente cliente = service.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }



}

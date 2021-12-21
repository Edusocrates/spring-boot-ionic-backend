package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.model.Pedido;
import com.edusocrates.cursoMC.serivce.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id){
        Pedido pedido= service.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }
    @PostMapping
    public ResponseEntity<?> insertPedido(@RequestBody Pedido pedido){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
                        return ResponseEntity.created(uri).build();
    }
}

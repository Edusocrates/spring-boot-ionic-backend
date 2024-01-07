package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.CategoriaDTO;
import com.edusocrates.cursoMC.DTO.PedidoDTO;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Pedido;
import com.edusocrates.cursoMC.serivce.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @PostMapping("/novo")
    public ResponseEntity<?> insertPedido( @RequestBody Pedido pedido){
         pedido = service.insertPedido(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
                        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDTO>> getAllCategoriasWithPagination(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                                             @RequestParam(name = "lines",defaultValue = "24") Integer linesPerPage,
                                                                             @RequestParam(name = "order",defaultValue = "nome") String orderBy,
                                                                             @RequestParam(name = "direction",defaultValue = "ASC") String direction){
        Page<Pedido> pedidosList = service.findAllWithPagenation(page,linesPerPage,orderBy,direction);
        Page<PedidoDTO> pedidoDTOList = pedidosList.map(pedido -> new PedidoDTO(pedido));
        return ResponseEntity.ok(pedidoDTOList);
    }

}

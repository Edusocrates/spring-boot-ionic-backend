package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;


    @GetMapping("{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Cliente cliente = service.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> getAllCategoriasWithPagination(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                           @RequestParam(name = "lines", defaultValue = "24") Integer linesPerPage,
                                                                           @RequestParam(name = "order", defaultValue = "nome") String orderBy,
                                                                           @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> clienteList = service.findAllWithPagenation(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> clienteDTOList = clienteList.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok(clienteDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO cliente = service.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        service.deleteCliente(id);
        return ResponseEntity.ok(null);
    }


}

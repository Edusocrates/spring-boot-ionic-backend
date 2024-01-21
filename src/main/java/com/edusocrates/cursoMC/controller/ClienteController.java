package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @GetMapping("/email")
    public ResponseEntity<Cliente> consultaClientePorEmail(@RequestParam(value = "value") String email) {
        Cliente cliente = service.consultaClientePorEmail(email);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<ClienteDTO>> getAllCategoriasWithPagination(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                           @RequestParam(name = "lines", defaultValue = "24") Integer linesPerPage,
                                                                           @RequestParam(name = "order", defaultValue = "nome") String orderBy,
                                                                           @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> clienteList = service.findAllWithPagenation(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> clienteDTOList = clienteList.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok(clienteDTOList);
    }

    @PostMapping
    public ResponseEntity<?> insertCliente(@RequestBody CreateClienteDTO createClienteDTO){
        ClienteDTO clienteDTO = service.insertCliente(createClienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(clienteDTO.getId()).toUri();
                return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody CreateClienteDTO createClienteDTO) {
        ClienteDTO cliente = service.updateCliente(id, createClienteDTO);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/picture")
    public ResponseEntity<?> uploadFotoPerfil(@RequestParam(name = "file") MultipartFile multipartFile){
        URI uri =  service.uploadFotoPerfil(multipartFile);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        service.deleteCliente(id);
        return ResponseEntity.ok(null);
    }


}

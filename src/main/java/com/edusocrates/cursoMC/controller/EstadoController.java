package com.edusocrates.cursoMC.controller;

import com.edusocrates.cursoMC.DTO.CidadeDTO;
import com.edusocrates.cursoMC.DTO.EstadoDTO;
import com.edusocrates.cursoMC.serivce.CidadeService;
import com.edusocrates.cursoMC.serivce.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {


    @Autowired
    private EstadoService service;


    @Autowired
    private CidadeService cidadeService;


    @GetMapping
    public ResponseEntity<List<EstadoDTO>> consultaEstados(){
        List<EstadoDTO> estado = service.consultaEstados();
        return ResponseEntity.ok(estado);
    }

    @GetMapping("{estado_id}/cidades")
    public ResponseEntity<List<CidadeDTO>> consultaCidadesDoEstado(@PathVariable("estado_id") Integer estadoId){
        List<CidadeDTO> cidadesDoEstado = cidadeService.consultaCidadesDoEstado(estadoId);
        return ResponseEntity.ok(cidadesDoEstado);
    }
}

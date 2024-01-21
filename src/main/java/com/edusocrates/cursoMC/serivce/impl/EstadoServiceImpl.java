package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.EstadoDTO;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Estado;
import com.edusocrates.cursoMC.repository.EstadoRepository;
import com.edusocrates.cursoMC.serivce.EstadoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {


    @Autowired
    private EstadoRepository repository;

    @Override
    public List<EstadoDTO> consultaEstados() {
        List<Estado> estado = repository.findAllByOrderByNome();
        List<EstadoDTO> estadoDTO = Estado.entityToDTOs(estado);
        return estadoDTO;
    }
}

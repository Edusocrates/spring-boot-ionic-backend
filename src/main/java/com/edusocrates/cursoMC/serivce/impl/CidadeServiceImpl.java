package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.CidadeDTO;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Cidade;
import com.edusocrates.cursoMC.model.Estado;
import com.edusocrates.cursoMC.repository.CidadeRepository;
import com.edusocrates.cursoMC.repository.EstadoRepository;
import com.edusocrates.cursoMC.serivce.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {


    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<CidadeDTO> consultaCidadesDoEstado(Integer estadoId) {
        Estado estado = estadoRepository.getById(estadoId);
        if(estado == null){
            throw new ObjectNotFoundException("codigo do Estado informado não foi encontrado!!");
        }
        List<Cidade> cidadeList = repository.findCidadesDoEstado(estadoId);
        if(cidadeList.isEmpty() || cidadeList == null){
            throw new ObjectNotFoundException("Estado não possui cidades cadastradas!");
        }
        List<CidadeDTO> cidadeDTOList = Cidade.entitiesToDTOs(cidadeList);

        return cidadeDTOList;
    }
}

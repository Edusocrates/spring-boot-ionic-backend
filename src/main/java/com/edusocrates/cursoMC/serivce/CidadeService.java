package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.CidadeDTO;
import com.edusocrates.cursoMC.model.Cidade;

import java.util.List;

public interface CidadeService {


    List<CidadeDTO> consultaCidadesDoEstado(Integer estadoId);

}

package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.repository.ClienteRepository;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente getClienteById(Integer id) {
        Cliente cliente = findById(id);
        return cliente;
    }

    private Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                +" Tipo: "+ Cliente.class.getName()));
    }
}

package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Pedido;
import com.edusocrates.cursoMC.repository.PedidoRepository;
import com.edusocrates.cursoMC.serivce.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Override
    public Pedido getPedidoById(Integer id) {
        Pedido pedido = findById(id);
        return pedido;
    }



     private Pedido findById(Integer id) {
            return repository.findById(id)
                    .orElseThrow(()->
                            new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                    +" Tipo: "+ Pedido.class.getName()));
        }
}

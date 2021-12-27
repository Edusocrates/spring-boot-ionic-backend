package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.PedidoDTO;
import com.edusocrates.cursoMC.model.Pedido;

public interface PedidoService {


    Pedido getPedidoById(Integer id);

    Pedido insertPedido(Pedido pedido);

    PedidoDTO insertPedidoTeste(PedidoDTO pedido);
}

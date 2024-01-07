package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.PedidoDTO;
import com.edusocrates.cursoMC.model.Pedido;
import org.springframework.data.domain.Page;

public interface PedidoService {


    Pedido getPedidoById(Integer id);

    Pedido insertPedido(Pedido pedido);

    PedidoDTO insertPedidoTeste(PedidoDTO pedido);

    Page<Pedido> findAllWithPagenation(Integer page, Integer linesPerPage,
                                       String orderBy, String direction);
}

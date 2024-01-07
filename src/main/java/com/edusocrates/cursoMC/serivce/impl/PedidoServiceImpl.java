package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.PedidoDTO;
import com.edusocrates.cursoMC.Utils.exceptions.AuthorizationException;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.*;
import com.edusocrates.cursoMC.model.enums.EstadoPagamento;
import com.edusocrates.cursoMC.repository.ItemPedidoRepository;
import com.edusocrates.cursoMC.repository.PagamentoRepository;
import com.edusocrates.cursoMC.repository.PedidoRepository;
import com.edusocrates.cursoMC.security.UserSS;
import com.edusocrates.cursoMC.serivce.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;


    @Override
    public Pedido getPedidoById(Integer id) {
        Pedido pedido = findById(id);
        return pedido;
    }

    @Override
    public Pedido insertPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.getClienteById(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = ( PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,pedido.getInstante());
        }
        pedido = repository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.getProdutoById(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.enviaConfirmacaoPedidoHTMLEmail(pedido);
        return pedido;
    }

    @Override
    public PedidoDTO insertPedidoTeste(PedidoDTO pedido) {
        Pedido novoPedido = new Pedido();
        BeanUtils.copyProperties(pedido,novoPedido);
        Pedido pedioNovo = repository.save(novoPedido);
        return new PedidoDTO(pedioNovo);
    }

    public Page<Pedido> findAllWithPagenation(Integer page, Integer linesPerPage,
                                                 String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso negado ao usuario");
        }
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.getClienteById(user.getId());
        return repository.findByCliente(cliente,pageRequest);
    }


    private Pedido findById(Integer id) {
            return repository.findById(id)
                    .orElseThrow(()->
                            new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                    +" Tipo: "+ Pedido.class.getName()));
        }
}

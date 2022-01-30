package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.model.*;
import com.edusocrates.cursoMC.model.enums.EstadoPagamento;
import com.edusocrates.cursoMC.model.enums.TipoCliente;
import com.edusocrates.cursoMC.repository.*;
import com.edusocrates.cursoMC.serivce.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBServiceImpl implements DBService {


    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    @Override
    public void instanteateDataBase() throws ParseException {
        Categoria categoria = new Categoria(null,"Informatica");
        Categoria categoria2 = new Categoria(null,"Escritorio");
        Categoria categoria3 = new Categoria(null,"Cama mesa e Banho");
        Categoria categoria4 = new Categoria(null,"Eletrônicos");
        Categoria categoria5 = new Categoria(null,"Jardinagem");
        Categoria categoria6 = new Categoria(null,"Decoração");
        Categoria categoria7 = new Categoria(null,"Perfumaria");

        Produto produto = new Produto(null,"Computador",2000.00);
        Produto produto2 = new Produto(null,"Impressora",800.00);
        Produto produto3 = new Produto(null,"Mouse",80.00);
        Produto produto4 = new Produto(null,"Mesa de escritório",300.00);
        Produto produto5 = new Produto(null,"Toalha",50.00);
        Produto produto6 = new Produto(null,"Colcha",200.00);
        Produto produto7 = new Produto(null,"TV true color",1880.00);
        Produto produto8 = new Produto(null,"Roçadeira",800.00);
        Produto produto9 = new Produto(null,"Abajour",180.00);
        Produto produto10 = new Produto(null,"Monitor 4k",890.00);
        Produto produto11 = new Produto(null,"Shampoo",90.00);

        categoria.getProdutos().addAll(Arrays.asList(produto,produto2,produto3,produto10));
        categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
        categoria3.getProdutos().addAll(Arrays.asList(produto5, produto6));
        categoria4.getProdutos().addAll(Arrays.asList(produto, produto2, produto3, produto7));
        categoria5.getProdutos().addAll(Arrays.asList(produto8));
        categoria6.getProdutos().addAll(Arrays.asList(produto9, produto10));
        categoria7.getProdutos().addAll(Arrays.asList(produto11));

        produto.getCategorias().addAll(Arrays.asList(categoria, categoria4));
        produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2, categoria4));
        produto3.getCategorias().addAll(Arrays.asList(categoria, categoria4));
        produto4.getCategorias().addAll(Arrays.asList(categoria2));
        produto5.getCategorias().addAll(Arrays.asList(categoria3));
        produto6.getCategorias().addAll(Arrays.asList(categoria3));
        produto7.getCategorias().addAll(Arrays.asList(categoria4));
        produto8.getCategorias().addAll(Arrays.asList(categoria5));
        produto9.getCategorias().addAll(Arrays.asList(categoria6));
        produto10.getCategorias().addAll(Arrays.asList(categoria6));
        produto11.getCategorias().addAll(Arrays.asList(categoria7));

        categoriaRepository.saveAll(Arrays.asList(categoria,categoria2,categoria3,
                categoria4,categoria5,categoria6,categoria7));
        produtoRepository.saveAll(Arrays.asList(produto,produto2,produto3,produto4,produto5,produto6,produto7,produto8,produto9,produto10,produto11));

        Estado estado = new Estado(null,"Minas Gerais");
        Estado estado2 =  new Estado(null,"São Paulo");

        Cidade cidade = new Cidade(null,"Uberlândia",estado);
        Cidade cidade2 = new Cidade(null,"São Paulo",estado2);
        Cidade cidade3 = new Cidade(null,"Campinas",estado2);

        estado.getCidades().addAll(Arrays.asList(cidade));
        estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));

        estadoRepository.saveAll(Arrays.asList(estado,estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade,cidade2,cidade3));

        Cliente cliente = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
        cliente.getTelefones().addAll(Arrays.asList("8094328423","7542394281"));
        Endereco endereco = new Endereco(null,"Rua flores", "300","Apartamento 3","Jardim","3822090",cliente,cidade);
        Endereco endereco2 = new Endereco(null,"Avenida Matos", "105","Sala 800","Centro","43191832",cliente,cidade2);

        cliente.getEnderecos().addAll(Arrays.asList(endereco,endereco2));

        clienteRepository.saveAll(Arrays.asList(cliente));
        enderecoRepository.saveAll(Arrays.asList(endereco,endereco2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido pedido = new Pedido(null,sdf.parse("30/09/2017 10:32"),cliente,endereco);
        Pedido pedido2 = new Pedido(null,sdf.parse("10/10/2017 10:32"),cliente,endereco2);

        Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO,pedido,6);
        pedido.setPagamento(pagamento);
        Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,pedido2,sdf.parse("20/10/2017 00:00"),null);
        pedido2.setPagamento(pagamento2);

        cliente.getPedidos().addAll(Arrays.asList(pedido,pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido,pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento,pagamento2));

        ItemPedido itemPedido = new ItemPedido(pedido,produto,0.00,1,2000.00);
        ItemPedido itemPedido2 = new ItemPedido(pedido,produto3,0.00,2,80.00);
        ItemPedido itemPedido3 = new ItemPedido(pedido2,produto2,100.00,1,800.00);
        pedido.getItens().addAll(Arrays.asList(itemPedido,itemPedido2));
        pedido2.getItens().addAll(Arrays.asList(itemPedido3));

        produto.getItens().addAll(Arrays.asList(itemPedido));
        produto2.getItens().addAll(Arrays.asList(itemPedido3));
        produto3.getItens().addAll(Arrays.asList(itemPedido2));

        itemPedidoRepository.saveAll(Arrays.asList(itemPedido,itemPedido2,itemPedido3));

    }
}

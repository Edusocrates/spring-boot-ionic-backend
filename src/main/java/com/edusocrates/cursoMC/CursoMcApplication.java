package com.edusocrates.cursoMC;

import com.edusocrates.cursoMC.model.*;
import com.edusocrates.cursoMC.model.enums.EstadoPagamento;
import com.edusocrates.cursoMC.model.enums.TipoCliente;
import com.edusocrates.cursoMC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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

		categoria.getProdutos().addAll(Arrays.asList(produto,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto.getCategorias().addAll(Arrays.asList(categoria));
		produto2.getCategorias().addAll(Arrays.asList(categoria,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria));

		categoriaRepository.saveAll(Arrays.asList(categoria,categoria2,categoria3,
				categoria4,categoria5,categoria6,categoria7));
		produtoRepository.saveAll(Arrays.asList(produto,produto2,produto3));

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

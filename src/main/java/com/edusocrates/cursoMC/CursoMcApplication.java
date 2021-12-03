package com.edusocrates.cursoMC;

import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Cidade;
import com.edusocrates.cursoMC.model.Estado;
import com.edusocrates.cursoMC.model.Produto;
import com.edusocrates.cursoMC.repository.CategoriaRepository;
import com.edusocrates.cursoMC.repository.CidadeRepository;
import com.edusocrates.cursoMC.repository.EstadoRepository;
import com.edusocrates.cursoMC.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria = new Categoria(null,"Informatica");
		Categoria categoria2 = new Categoria(null,"Escritorio");

		Produto produto = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);

		categoria.getProdutos().addAll(Arrays.asList(produto,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto.getCategorias().addAll(Arrays.asList(categoria));
		produto2.getCategorias().addAll(Arrays.asList(categoria,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria));

		categoriaRepository.saveAll(Arrays.asList(categoria,categoria2));
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



	}
}

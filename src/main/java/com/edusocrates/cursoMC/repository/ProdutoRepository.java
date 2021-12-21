package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto,Integer> {

    @Query(nativeQuery = true,value = "SELECT DISTINCT prod.* FROM Produto prod INNER JOIN Categoria cat " +
            "WHERE prod.nome LIKE %:nome% AND cat.id IN :categorias")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categoriaList, Pageable pageRequest);
}

package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto,Integer> {
}

package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM ESTADO WHERE NOME =:nome")
    Estado consultaEstadoPorNome(@Param("nome") String nome);

    @Transactional(readOnly = true)//encontra e ordena por nome
    List<Estado> findAllByOrderByNome();
}

package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM CIDADE OBJ WHERE ESTADO_ID =:estadoId")
    List<Cidade>  findCidadesDoEstado(@Param("estadoId") Integer estadoId);

}

package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Query(nativeQuery = true,value = "SELECT email FROM CLIENTE WHERE email =:email")
    String findByEmail(@Param("email") String email);

    @Query(nativeQuery = true,value = "SELECT * FROM CLIENTE WHERE email =:email")
    Cliente buscarClientePorEmail(@Param("email") String email);
}

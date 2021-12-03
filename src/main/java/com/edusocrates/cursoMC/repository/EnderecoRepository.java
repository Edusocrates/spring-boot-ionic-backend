package com.edusocrates.cursoMC.repository;

import com.edusocrates.cursoMC.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {
}

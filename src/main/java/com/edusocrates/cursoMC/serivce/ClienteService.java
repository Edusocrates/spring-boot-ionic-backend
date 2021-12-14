package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.model.Cliente;
import org.springframework.data.domain.Page;

public interface ClienteService {
    Cliente getClienteById(Integer id);

    Page<Cliente> findAllWithPagenation(Integer page, Integer linesPerPage, String orderBy, String direction);

    void deleteCliente(Integer id);

    ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO);

    ClienteDTO insertCliente(CreateClienteDTO createClienteDTO);
}

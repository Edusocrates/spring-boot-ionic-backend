package com.edusocrates.cursoMC.serivce;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface ClienteService {
    Cliente getClienteById(Integer id);

    Page<Cliente> findAllWithPagenation(Integer page, Integer linesPerPage, String orderBy, String direction);

    void deleteCliente(Integer id);

    ClienteDTO updateCliente(Integer id, CreateClienteDTO createClienteDTO);

    ClienteDTO insertCliente(CreateClienteDTO createClienteDTO);

    URI uploadFotoPerfil(MultipartFile multipartFile);

    Cliente consultaClientePorEmail(String email);
}

package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.exception.DataIntegrityException;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.repository.ClienteRepository;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente getClienteById(Integer id) {
        Cliente cliente = findById(id);
        return cliente;
    }

    @Override
    public Page<Cliente> findAllWithPagenation(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest =  PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    @Override
    public void deleteCliente(Integer id) {
        Cliente cliente = findById(id);
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir um cliente que possui amarracoes!!");
        }

    }

    @Override
    public ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO) {
        Cliente clienteBase = findById(id);
        clienteDTO.setId(id);
        if(clienteDTO.getEmail() == null || clienteDTO.getEmail().isEmpty()){
            clienteDTO.setEmail(clienteBase.getEmail());
        }
        BeanUtils.copyProperties(clienteDTO,clienteBase);
        Cliente updatedCliente = repository.save(clienteBase);
        return new ClienteDTO(updatedCliente);
    }

    private Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                +" Tipo: "+ Cliente.class.getName()));
    }
}

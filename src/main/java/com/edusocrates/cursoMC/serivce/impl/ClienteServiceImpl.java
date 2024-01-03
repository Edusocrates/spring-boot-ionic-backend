package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.exception.DataIntegrityException;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Cidade;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.model.Endereco;
import com.edusocrates.cursoMC.repository.ClienteRepository;
import com.edusocrates.cursoMC.repository.EnderecoRepository;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EnderecoRepository enderecoRepository;

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
            throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedidos!!");
        }

    }

    @Override
    public ClienteDTO updateCliente(Integer id, CreateClienteDTO createClienteDTO) {
        Cliente clienteBase = findById(id);
        String validEmail = repository.findByEmail(createClienteDTO.getEmail());
        if(!validEmail.isEmpty()){
            throw new DataIntegrityException("email já existente na base!");
        }
        BeanUtils.copyProperties(createClienteDTO,clienteBase);
        Cliente updatedCliente = repository.save(clienteBase);
        return new ClienteDTO(updatedCliente);
    }

    @Override
    public ClienteDTO insertCliente(CreateClienteDTO createClienteDTO) {
        String validEmail = repository.findByEmail(createClienteDTO.getEmail());
        if(validEmail != null){
            throw new DataIntegrityException("email já existente na base!");
        }
        Cliente cliente = new Cliente();
        //seta a sennha criptografada que o cliente digitou
        createClienteDTO.setSenha(pe.encode(createClienteDTO.getSenha()));
        cliente = cliente.fromDto(createClienteDTO);
        Cliente savedCliente = repository.save(cliente);
        enderecoRepository.save(cliente.getEnderecos().get(0));
        return new ClienteDTO(savedCliente);
    }

    private Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                +" Tipo: "+ Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }
//não implementado pq precisa ajustar o Direction
//    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
//        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
//        return repository.findAll(pageRequest);
//    }



}

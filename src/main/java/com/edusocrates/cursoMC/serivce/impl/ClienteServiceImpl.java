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
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

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

    @Override
    public ClienteDTO insertCliente(CreateClienteDTO createClienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(createClienteDTO.getNome());
        cliente.setTipo(createClienteDTO.getTipo());
        cliente.setCpfOuCnpj(createClienteDTO.getCpfOuCnpj());
        cliente.setEmail(createClienteDTO.getEmail());

        Cidade cidade = new Cidade();
        cidade.setId(createClienteDTO.getCidadeId());

        Endereco endereco = new Endereco();
        endereco.setCliente(cliente);
        endereco.setBairro(createClienteDTO.getBairro());
        endereco.setCep(createClienteDTO.getCep());
        endereco.setComplemento(createClienteDTO.getComplemento());
        endereco.setLogradouro(createClienteDTO.getLogradouro());
        endereco.setNumero(createClienteDTO.getNumero());
        endereco.setCidade(cidade);


        cliente.getEnderecos().add(endereco);


        cliente.getTelefones().add(createClienteDTO.getTelefone());
        if(createClienteDTO.getTelefone2() != null){
            cliente.getTelefones().add(createClienteDTO.getTelefone2());
        }
        if(createClienteDTO.getTelefone3() != null){
            cliente.getTelefones().add(createClienteDTO.getTelefone3());
        }
        Cliente savedCliente = repository.save(cliente);
        enderecoRepository.save(endereco);
        return new ClienteDTO(savedCliente);
    }

    private Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ObjectNotFoundException("Objeto não encontrado! Id: "+id
                                +" Tipo: "+ Cliente.class.getName()));
    }
}

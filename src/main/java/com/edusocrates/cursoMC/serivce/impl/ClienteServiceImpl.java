package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.Utils.exceptions.AuthorizationException;
import com.edusocrates.cursoMC.Utils.exceptions.FileException;
import com.edusocrates.cursoMC.exception.DataIntegrityException;
import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Categoria;
import com.edusocrates.cursoMC.model.Cidade;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.model.Endereco;
import com.edusocrates.cursoMC.model.enums.Perfil;
import com.edusocrates.cursoMC.repository.ClienteRepository;
import com.edusocrates.cursoMC.repository.EnderecoRepository;
import com.edusocrates.cursoMC.security.UserSS;
import com.edusocrates.cursoMC.serivce.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
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

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefixo;

    @Override
    public Cliente getClienteById(Integer id) {
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado ao usuario!");
        }


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

    @Override
    public URI uploadFotoPerfil(MultipartFile multipartFile) {
            UserSS user = UserService.authenticated();

            if (user == null){
                throw new AuthorizationException("Acesso negado ao usuario");
            }

            BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
            String nomeArquivo = prefixo + user.getId()+ ".jpg";
            return s3Service.uploadFile(imageService.getInputStream(jpgImage,"jpg"),nomeArquivo,"image");
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

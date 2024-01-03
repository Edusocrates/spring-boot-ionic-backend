package com.edusocrates.cursoMC.model;

import com.edusocrates.cursoMC.DTO.ClienteDTO;
import com.edusocrates.cursoMC.DTO.CreateClienteDTO;
import com.edusocrates.cursoMC.exception.DataIntegrityException;
import com.edusocrates.cursoMC.model.enums.Perfil;
import com.edusocrates.cursoMC.model.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "cpfOuCnpj",nullable = false,unique = true)
    private String  cpfOuCnpj;

    @Column(name = "tipo", nullable = false)
    @Enumerated
    private TipoCliente tipo;

    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)//eager serve para buscar os perfis juntos pára garantir sempre que buscar o cliente, trazer o perfil
    @CollectionTable(name = "PERFIS")
    private Set<Integer>  perfis = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Set<Perfil> getPerfis() {
        //pega o perfil inserido e transforma no codigo do perfil
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        //salva o codigo do perfil
        perfis.add(perfil.getCodigo());
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

//    public void setTipo(Integer tipo) {
//        this.tipo = tipo;
//    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);//transforma todos os clientes com o perfil padrão de cliente
    }

    public Cliente() {
        addPerfil(Perfil.CLIENTE);//transforma todos os clientes com o perfil padrão de cliente
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Cliente fromDto(CreateClienteDTO createClienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(createClienteDTO.getNome());
        cliente.setTipo(createClienteDTO.getTipo());
        cliente.setCpfOuCnpj(createClienteDTO.getCpfOuCnpj());
        cliente.setEmail(createClienteDTO.getEmail());
        //seta a senha que ja foi criptografada no clienteService
        cliente.setSenha(createClienteDTO.getSenha());
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
        return cliente;
    }

    public Cliente fromDto(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(),clienteDTO.getNome(), clienteDTO.getEmail(), null, null,null);
    }



}

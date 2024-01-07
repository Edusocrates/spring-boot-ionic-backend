package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.exception.ObjectNotFoundException;
import com.edusocrates.cursoMC.model.Cliente;
import com.edusocrates.cursoMC.repository.ClienteRepository;
import com.edusocrates.cursoMC.serivce.AuthService;
import com.edusocrates.cursoMC.serivce.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;


    private Random rand = new Random();

    @Override
    public void enviaNovaSenha(String email) {
        Cliente cliente = clienteRepository.buscarClientePorEmail(email);
        if(cliente == null){
            throw new ObjectNotFoundException("Cliente não encontrado!");
        }
        String novaSenha = novaSenha();
        cliente.setSenha(pe.encode(novaSenha));
        clienteRepository.save(cliente);
        emailService.enviaNovaSenhaPorEmail(cliente,novaSenha);

    }

    private String novaSenha() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        //metodo que gera caracteres aleatorios
        int opt = rand.nextInt(3);//seleciona um numero aleatorio de 0 até 3
        if(opt == 0){// gera um digito
            return (char) (rand.nextInt(10)+48);
        }else if (opt == 1){// gera letra maiuscula
            return (char) (rand.nextInt(26)+65);
        }else{//gera letra minuscula
            return (char) (rand.nextInt(26)+97);
        }
    }


}

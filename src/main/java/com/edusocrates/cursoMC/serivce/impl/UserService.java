package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    public static UserSS authenticated() {
        //retorna o usuario logado no sistema
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e ){
            return null;
        }

    }



}

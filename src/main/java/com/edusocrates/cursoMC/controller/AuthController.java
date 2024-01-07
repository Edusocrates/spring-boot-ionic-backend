package com.edusocrates.cursoMC.controller;


import com.edusocrates.cursoMC.DTO.EmailDTO;
import com.edusocrates.cursoMC.security.UserSS;
import com.edusocrates.cursoMC.security.utils.JWTUtil;
import com.edusocrates.cursoMC.serivce.AuthService;
import com.edusocrates.cursoMC.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization","Bearer "+ token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@RequestBody EmailDTO emailDTO){
        authService.enviaNovaSenha(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }


}

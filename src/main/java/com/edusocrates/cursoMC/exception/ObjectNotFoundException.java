package com.edusocrates.cursoMC.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long SerialVersionUID = 1L;

    public ObjectNotFoundException(String mensagem){
        super(mensagem);
    }
    public ObjectNotFoundException(String mensagem, Throwable cause){
        super(mensagem,cause);
    }
}

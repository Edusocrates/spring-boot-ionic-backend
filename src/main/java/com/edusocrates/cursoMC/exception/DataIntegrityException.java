package com.edusocrates.cursoMC.exception;

public class DataIntegrityException extends RuntimeException {

    private static final long SerialVersionUID = 1L;

    public DataIntegrityException(String mensagem){
            super(mensagem);
        }
        public DataIntegrityException(String mensagem, Throwable cause){
            super(mensagem,cause);
        }
}

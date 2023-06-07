package com.clinica.odontologica.exception;

public class ResourceNotFoundException  extends Exception{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
package com.dh.clinica.exceptions;

public class ResourceNotFoundException  extends Exception{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
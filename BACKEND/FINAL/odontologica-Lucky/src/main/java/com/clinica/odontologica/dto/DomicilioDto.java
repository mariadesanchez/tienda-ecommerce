package com.dh.clinica.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public DomicilioDto () {
    }
}

package com.sunbelt.clientemanager.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Pattern(regexp = "C|P", message = "El tipo de documento debe ser 'C' o 'P'")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Pattern(regexp = "\\d+", message = "El número del documento debe ser numérico")
    private String numeroDocumento;

}

package br.com.futurodev.doaville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String nomeUsuario;
    @NotBlank
    private String senha;
}
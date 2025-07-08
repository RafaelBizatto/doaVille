package br.com.futurodev.doaville.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemDoacaoDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
}
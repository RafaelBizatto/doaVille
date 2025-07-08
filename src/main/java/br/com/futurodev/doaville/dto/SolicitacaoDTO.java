package br.com.futurodev.doaville.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitacaoDTO {
    @NotNull(message = "ID do item é obrigatório")
    private Long idItemDoacao;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private Integer quantidade;

    @NotBlank(message = "Endereço de entrega é obrigatório")
    private String enderecoEntrega;

    @NotBlank(message = "Bairro de entrega é obrigatório")
    private String bairroEntrega;
}
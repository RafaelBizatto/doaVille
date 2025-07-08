package br.com.futurodev.doaville.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.OffsetDateTime;

@Entity
@Table(name = "solicitacao_doacao")
@Data
@EqualsAndHashCode(of = "id")
public class SolicitacaoDoacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_item_doacao", nullable = false)
    private ItemDoacao itemDoacao;

    private Integer quantidade;
    private OffsetDateTime dataSolicitacao;
    private String enderecoEntrega;
    private String bairroEntrega;
}
package br.com.futurodev.doaville.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "item_doacao")
@Data
@EqualsAndHashCode(of = "id")
public class ItemDoacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String descricao;
    private boolean ativo = true;
}
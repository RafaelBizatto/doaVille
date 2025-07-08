package br.com.futurodev.doaville.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "nome_usuario", unique = true)
    private String nomeUsuario;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public enum Perfil {
        ADMIN, USER
    }
}
package br.com.futurodev.doaville.dto;

import br.com.futurodev.doaville.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDetalhesDTO {

    private Long id;
    private String nome;
    private String nomeUsuario;
    private Usuario.Perfil perfil;

    public UsuarioDetalhesDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.nomeUsuario = usuario.getNomeUsuario();
        this.perfil = usuario.getPerfil();
    }
}
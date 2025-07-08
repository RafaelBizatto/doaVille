package br.com.futurodev.doaville.service;

import br.com.futurodev.doaville.exception.ResourceNotFoundException;
import br.com.futurodev.doaville.model.Usuario;
import br.com.futurodev.doaville.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }
}
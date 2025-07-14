package br.com.futurodev.doaville.service;

import br.com.futurodev.doaville.dto.UsuarioDTO;
import br.com.futurodev.doaville.dto.UsuarioDetalhesDTO;
import br.com.futurodev.doaville.exception.ResourceNotFoundException;
import br.com.futurodev.doaville.model.Usuario;
import br.com.futurodev.doaville.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Injetar o PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- MÉTODOS NOVOS E ATUALIZADOS ---

    @Transactional
    public UsuarioDetalhesDTO criarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByNomeUsuario(dto.getNomeUsuario()).isPresent()) {
            throw new RuntimeException("Nome de usuário já existe: " + dto.getNomeUsuario());
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setNomeUsuario(dto.getNomeUsuario());
        // Criptografar a senha antes de salvar
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setPerfil(dto.getPerfil());

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return new UsuarioDetalhesDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioDetalhesDTO editarUsuario(Long id, UsuarioDTO dto) {
        if (usuarioRepository.existsByNomeUsuarioAndIdNot(dto.getNomeUsuario(), id)) {
            throw new RuntimeException("Nome de usuário já pertence a outro usuário: " + dto.getNomeUsuario());
        }

        Usuario usuarioExistente = this.buscarPorId(id);
        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setNomeUsuario(dto.getNomeUsuario());
        usuarioExistente.setPerfil(dto.getPerfil());
        // Atualizar a senha apenas se uma nova for fornecida
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuarioExistente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return new UsuarioDetalhesDTO(usuarioAtualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        // Adicionar validação para não se auto-deletar ou deletar 'admin' (opcional, mas bom)
        usuarioRepository.deleteById(id);
    }

    // --- MÉTODOS DE CONSULTA ATUALIZADOS PARA USAR DTO ---

    public List<UsuarioDetalhesDTO> listarTodos() {
        return usuarioRepository.findAll()
            .stream()
            .map(UsuarioDetalhesDTO::new) // Converte cada Usuario em UsuarioDetalhesDTO
            .collect(Collectors.toList());
    }

    public UsuarioDetalhesDTO buscarDetalhesPorId(Long id) {
        Usuario usuario = this.buscarPorId(id);
        return new UsuarioDetalhesDTO(usuario);
    }

    // Método auxiliar que continua retornando a entidade completa para uso interno
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }
}
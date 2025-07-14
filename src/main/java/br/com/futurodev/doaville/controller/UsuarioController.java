package br.com.futurodev.doaville.controller;

import br.com.futurodev.doaville.dto.UsuarioDTO;
import br.com.futurodev.doaville.dto.UsuarioDetalhesDTO;
import br.com.futurodev.doaville.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@PreAuthorize("hasRole('ADMIN')") // Protege todos os endpoints deste controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDetalhesDTO> criar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDetalhesDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDetalhesDTO>> listarTodos() {
        // Agora retorna uma lista de DTOs, sem senhas!
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalhesDTO> buscarPorId(@PathVariable Long id) {
        // Agora retorna um DTO, sem senha!
        return ResponseEntity.ok(usuarioService.buscarDetalhesPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalhesDTO> editar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDetalhesDTO usuarioAtualizado = usuarioService.editarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
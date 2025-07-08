package br.com.futurodev.doaville.controller;

import br.com.futurodev.doaville.dto.ItemDoacaoDTO;
import br.com.futurodev.doaville.model.ItemDoacao;
import br.com.futurodev.doaville.service.ItemDoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-doacao")
@PreAuthorize("hasRole('ADMIN')") // Garante que apenas ADMINS acessem estes endpoints
public class ItemDoacaoController {

    @Autowired
    private ItemDoacaoService itemDoacaoService;

    @PostMapping
    public ResponseEntity<ItemDoacao> criar(@Valid @RequestBody ItemDoacaoDTO itemDTO) {
        ItemDoacao novoItem = itemDoacaoService.criarItem(itemDTO);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemDoacao>> listarTodos() {
        List<ItemDoacao> itens = itemDoacaoService.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDoacao> buscarPorId(@PathVariable Long id) {
        ItemDoacao item = itemDoacaoService.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDoacao> editar(@PathVariable Long id, @Valid @RequestBody ItemDoacaoDTO itemDTO) {
        ItemDoacao itemAtualizado = itemDoacaoService.editarItem(id, itemDTO);
        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemDoacaoService.deletarLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
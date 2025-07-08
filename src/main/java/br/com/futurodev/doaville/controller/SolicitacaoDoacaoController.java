package br.com.futurodev.doaville.controller;

import br.com.futurodev.doaville.dto.SolicitacaoDTO;
import br.com.futurodev.doaville.model.SolicitacaoDoacao;
import br.com.futurodev.doaville.service.SolicitacaoDoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoDoacaoController {

    @Autowired
    private SolicitacaoDoacaoService solicitacaoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SolicitacaoDoacao> criar(@Valid @RequestBody SolicitacaoDTO solicitacaoDTO) {
        SolicitacaoDoacao novaSolicitacao = solicitacaoService.criar(solicitacaoDTO);
        return new ResponseEntity<>(novaSolicitacao, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SolicitacaoDoacao>> listar(@RequestParam(name = "idItem", required = false) Long idItem) {
        List<SolicitacaoDoacao> solicitacoes = solicitacaoService.listar(idItem);
        return ResponseEntity.ok(solicitacoes);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        solicitacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
package br.com.futurodev.doaville.service;

import br.com.futurodev.doaville.dto.SolicitacaoDTO;
import br.com.futurodev.doaville.exception.ResourceNotFoundException;
import br.com.futurodev.doaville.model.ItemDoacao;
import br.com.futurodev.doaville.model.SolicitacaoDoacao;
import br.com.futurodev.doaville.repository.ItemDoacaoRepository;
import br.com.futurodev.doaville.repository.SolicitacaoDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SolicitacaoDoacaoService {

    @Autowired
    private SolicitacaoDoacaoRepository solicitacaoRepository;

    @Autowired
    private ItemDoacaoRepository itemRepository;

    @Transactional
    public SolicitacaoDoacao criar(SolicitacaoDTO dto) {
        ItemDoacao item = itemRepository.findById(dto.getIdItemDoacao())
            .orElseThrow(() -> new ResourceNotFoundException("Item de doação não encontrado com o ID: " + dto.getIdItemDoacao()));

        if (!item.isAtivo()) {
            throw new RuntimeException("Não é possível solicitar um item que está inativo.");
        }

        SolicitacaoDoacao solicitacao = new SolicitacaoDoacao();
        solicitacao.setItemDoacao(item);
        solicitacao.setQuantidade(dto.getQuantidade());
        solicitacao.setEnderecoEntrega(dto.getEnderecoEntrega());
        solicitacao.setBairroEntrega(dto.getBairroEntrega());
        solicitacao.setDataSolicitacao(OffsetDateTime.now());

        return solicitacaoRepository.save(solicitacao);
    }

    public List<SolicitacaoDoacao> listar(Long idItem) {
        if (idItem != null) {
            return solicitacaoRepository.findByItemDoacaoId(idItem);
        } else {
            return solicitacaoRepository.findAll();
        }
    }

    @Transactional
    public void deletar(Long id) {
        // Verifica se a solicitação existe antes de tentar deletar
        if (!solicitacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Solicitação não encontrada com o ID: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }
}
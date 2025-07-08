package br.com.futurodev.doaville.service;

import br.com.futurodev.doaville.dto.ItemDoacaoDTO;
import br.com.futurodev.doaville.exception.ResourceNotFoundException;
import br.com.futurodev.doaville.model.ItemDoacao;
import br.com.futurodev.doaville.repository.ItemDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemDoacaoService {

    @Autowired
    private ItemDoacaoRepository itemRepository;

    @Transactional
    public ItemDoacao criarItem(ItemDoacaoDTO dto) {
        if (itemRepository.existsByNome(dto.getNome())) {
            throw new RuntimeException("Já existe um item com o nome: " + dto.getNome());
        }
        ItemDoacao item = new ItemDoacao();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        return itemRepository.save(item);
    }

    public List<ItemDoacao> listarTodos() {
        return itemRepository.findAll();
    }

    public ItemDoacao buscarPorId(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + id));
    }

    @Transactional
    public ItemDoacao editarItem(Long id, ItemDoacaoDTO dto) {
        if (itemRepository.existsByNomeAndIdNot(dto.getNome(), id)) {
            throw new RuntimeException("Já existe outro item com o nome: " + dto.getNome());
        }
        ItemDoacao itemExistente = this.buscarPorId(id);
        itemExistente.setNome(dto.getNome());
        itemExistente.setDescricao(dto.getDescricao());
        return itemRepository.save(itemExistente);
    }

    @Transactional
    public void deletarLogicamente(Long id) {
        ItemDoacao item = this.buscarPorId(id);
        item.setAtivo(false); // Exclusão lógica
        itemRepository.save(item);
    }
}
package br.com.futurodev.doaville.repository;

import br.com.futurodev.doaville.model.ItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoacaoRepository extends JpaRepository<ItemDoacao, Long> {
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);
}
package br.com.futurodev.doaville.repository;

import br.com.futurodev.doaville.model.SolicitacaoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoDoacaoRepository extends JpaRepository<SolicitacaoDoacao, Long> {
    List<SolicitacaoDoacao> findByItemDoacaoId(Long idItem);
}
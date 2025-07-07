import jakarta.persistence.*;
import lombok.Data; // Ou gere getters/setters manualmente

@Entity
@Table(name = "item_doacao")
@Data
public class ItemDoacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true; // Valor padrão
}
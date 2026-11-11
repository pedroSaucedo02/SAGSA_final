package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "padroes_desempenho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PadraoDesempenho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subfuncao_id", nullable = false)
    @NotNull
    private Subfuncao subfuncao;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricao;
}

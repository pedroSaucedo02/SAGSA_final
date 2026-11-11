package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sapz_estrategias_ensino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzEstrategiaEnsino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "nome_estrategia", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nomeEstrategia;

    @Column(name = "descricao_atividade", columnDefinition = "TEXT")
    private String descricaoAtividade;
}

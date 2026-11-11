package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sapz_criterio_rubricas", uniqueConstraints = @UniqueConstraint(name = "uq_criterio_nivel", columnNames = {"criterio_id", "nivel"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzCriterioRubrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "criterio_id", nullable = false)
    @NotNull
    private SapzCriterioAvaliacao criterio;

    @Column(name = "nivel", nullable = false)
    @NotNull
    private Integer nivel;

    @Column(name = "descricao_rubrica", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricaoRubrica;

    @Column(name = "pontuacao", precision = 5, scale = 2, nullable = false)
    @NotNull
    private BigDecimal pontuacao = BigDecimal.ZERO;
}

package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sapz_criterios_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzCriterioAvaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instrumento_id", nullable = false)
    @NotNull
    private SapzInstrumentoAvaliacao instrumento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "capacidade_id", nullable = false)
    @NotNull
    private Capacidade capacidade;

    @Column(name = "descricao_criterio", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricaoCriterio;

    @Column(name = "prioridade_peso", precision = 5, scale = 2)
    private BigDecimal prioridadePeso = new BigDecimal("1.00");
}

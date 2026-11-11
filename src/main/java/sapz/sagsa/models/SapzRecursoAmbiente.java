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
@Table(name = "sapz_recursos_ambientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzRecursoAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "ambiente_pedagogico", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150)
    private String ambientePedagogico;

    @Column(name = "recursos_didaticos", columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String recursosDidaticos;
}

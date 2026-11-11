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
@Table(name = "sapz_instrumentos_avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzInstrumentoAvaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "nome_instrumento", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nomeInstrumento;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "tipo_metodo", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String tipoMetodo = "GRADUAL";
}

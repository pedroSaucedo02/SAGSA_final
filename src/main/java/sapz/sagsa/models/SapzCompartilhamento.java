package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sapz_compartilhamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzCompartilhamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull
    private SituacaoAprendizagem situacaoAprendizagem;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "usuario_destinatario_id")
    private Usuario usuarioDestinatario;

    @Column(name = "tipo_compartilhamento", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String tipoCompartilhamento;

    @Column(name = "data_compartilhamento", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataCompartilhamento;
}

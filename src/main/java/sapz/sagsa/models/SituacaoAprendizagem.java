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
@Table(name = "situacoes_aprendizagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoAprendizagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String titulo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    @NotNull
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instrutor_id", nullable = false)
    @NotNull
    private Usuario instrutor;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column(name = "tipo_estrategia", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String tipoEstrategia;

    @Column(name = "contextualizacao", columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String contextualizacao;

    @Column(name = "desafio", columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String desafio;

    @Column(name = "resultados_esperados", columnDefinition = "TEXT", nullable = false)
    @NotBlank
    private String resultadosEsperados;

    @Column(name = "carga_horaria_sa", nullable = false)
    @NotNull
    private Integer cargaHorariaSa;

    @Column(name = "compartilhada", nullable = false)
    @NotNull
    private Boolean compartilhada = false;

    @Column(name = "data_criacao", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;
}

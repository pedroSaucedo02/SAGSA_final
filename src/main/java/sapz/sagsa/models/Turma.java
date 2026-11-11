package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "escola_id", nullable = false)
    @NotNull
    private Escola escola;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_curso_id", nullable = false)
    @NotNull
    private PlanoCurso planoCurso;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Usuario coordenador;

    @Column(name = "codigo_turma", length = 50, nullable = false, unique = true)
    @NotBlank
    @Size(max = 50)
    private String codigoTurma;

    @Column(name = "ano", nullable = false)
    @NotNull
    private Integer ano;

    @Column(name = "semestre", nullable = false)
    @NotNull
    private Integer semestre;

    @Column(name = "data_inicio", nullable = false)
    @NotNull
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    @NotNull
    private LocalDate dataFim;

    @Column(name = "ativo", nullable = false)
    @NotNull
    private Boolean ativo = true;
}

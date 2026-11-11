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
@Table(name = "planos_curso", uniqueConstraints = @UniqueConstraint(name = "uq_plano_versao", columnNames = {"curso_id", "codigo_plano", "versao"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull
    private Curso curso;

    @Column(name = "codigo_plano", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String codigoPlano;

    @Column(name = "versao", length = 20, nullable = false)
    @NotBlank
    @Size(max = 20)
    private String versao = "1.0";

    @Column(name = "carga_horaria_total", nullable = false)
    @NotNull
    private Integer cargaHorariaTotal;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "ativo", nullable = false)
    @NotNull
    private Boolean ativo = true;
}

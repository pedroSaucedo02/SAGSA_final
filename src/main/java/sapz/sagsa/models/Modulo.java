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
@Table(name = "modulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_curso_id", nullable = false)
    @NotNull
    private PlanoCurso planoCurso;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nome;

    @Column(name = "tipo_modulo", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String tipoModulo;

    @Column(name = "ordem", nullable = false)
    @NotNull
    private Integer ordem = 1;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull
    private Integer cargaHoraria;
}

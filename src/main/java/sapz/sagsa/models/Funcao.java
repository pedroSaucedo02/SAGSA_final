package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcoes", uniqueConstraints = @UniqueConstraint(name = "uq_funcao_plano", columnNames = {"plano_curso_id", "numero"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_curso_id", nullable = false)
    @NotNull
    private PlanoCurso planoCurso;

    @Column(name = "numero", nullable = false)
    @NotNull
    private Integer numero;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricao;
}

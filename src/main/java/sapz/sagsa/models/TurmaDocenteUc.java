package sapz.sagsa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turma_docentes_ucs")
@IdClass(TurmaDocenteUcId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDocenteUc {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    private UnidadeCurricular unidadeCurricular;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Usuario instrutor;
}

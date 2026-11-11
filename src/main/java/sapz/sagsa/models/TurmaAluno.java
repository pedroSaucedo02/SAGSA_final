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
@Table(name = "turma_alunos")
@IdClass(TurmaAlunoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaAluno {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(name = "data_matricula", nullable = false, insertable = false, updatable = false)
    private LocalDate dataMatricula;

    @Column(name = "status", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String status = "MATRICULADO";
}

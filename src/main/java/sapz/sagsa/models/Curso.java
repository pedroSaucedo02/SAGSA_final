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
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_tecnologica_id")
    private AreaTecnologica areaTecnologica;

    @Column(name = "nome", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150)
    private String nome;

    @Column(name = "tipo_curso", length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String tipoCurso;

    @Column(name = "ativo", nullable = false)
    @NotNull
    private Boolean ativo = true;
}

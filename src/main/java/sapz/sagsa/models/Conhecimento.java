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
@Table(name = "conhecimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    @NotNull
    private UnidadeCurricular unidadeCurricular;

    @Column(name = "topico", length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String topico;

    @Column(name = "detalhamento", columnDefinition = "TEXT")
    private String detalhamento;
}

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
@Table(name = "capacidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Capacidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    @NotNull
    private UnidadeCurricular unidadeCurricular;

    @Column(name = "tipo", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String tipo;

    @Column(name = "dominio", length = 30, nullable = false)
    @NotBlank
    @Size(max = 30)
    private String dominio;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricao;
}

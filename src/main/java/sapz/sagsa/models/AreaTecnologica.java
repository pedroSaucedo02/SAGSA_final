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
@Table(name = "areas_tecnologicas", uniqueConstraints = @UniqueConstraint(name = "uq_eixo_area", columnNames = {"eixo_tecnologico_id", "nome"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaTecnologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eixo_tecnologico_id", nullable = false)
    @NotNull
    private EixoTecnologico eixoTecnologico;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nome;
}

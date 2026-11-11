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
@Table(name = "subfuncoes", uniqueConstraints = @UniqueConstraint(name = "uq_subfuncao_funcao", columnNames = {"funcao_id", "codigo_subfuncao"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subfuncao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "funcao_id", nullable = false)
    @NotNull
    private Funcao funcao;

    @Column(name = "codigo_subfuncao", length = 20, nullable = false)
    @NotBlank
    @Size(max = 20)
    private String codigoSubfuncao;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String descricao;
}

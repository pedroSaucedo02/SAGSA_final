package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "escolas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150)
    private String nome;

    @Column(name = "codigo_unidade", length = 20, unique = true)
    @Size(max = 20)
    private String codigoUnidade;

    @Column(name = "cidade", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String cidade;

    @Column(name = "estado", columnDefinition = "CHAR(2)", length = 2, nullable = false)
    @NotBlank
    @Size(min = 2, max = 2)
    private String estado = "RS";

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}

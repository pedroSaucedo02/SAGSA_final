package sapz.sagsa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "capacidade_padrao_desempenho")
@IdClass(CapacidadePadraoDesempenhoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapacidadePadraoDesempenho {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "capacidade_id", nullable = false)
    private Capacidade capacidade;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "padrao_desempenho_id", nullable = false)
    private PadraoDesempenho padraoDesempenho;
}

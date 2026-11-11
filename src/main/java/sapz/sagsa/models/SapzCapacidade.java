package sapz.sagsa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sapz_capacidades")
@IdClass(SapzCapacidadeId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzCapacidade {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "capacidade_id", nullable = false)
    private Capacidade capacidade;
}

package sapz.sagsa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sapz_conhecimentos")
@IdClass(SapzConhecimentoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzConhecimento {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    private SituacaoAprendizagem situacaoAprendizagem;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "conhecimento_id", nullable = false)
    private Conhecimento conhecimento;
}

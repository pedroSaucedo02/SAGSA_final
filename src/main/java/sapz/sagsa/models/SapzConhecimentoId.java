package sapz.sagsa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SapzConhecimentoId implements Serializable {
    private Long situacaoAprendizagem;
    private Long conhecimento;
}

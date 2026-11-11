package sapz.sagsa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapacidadePadraoDesempenhoId implements Serializable {
    private Long capacidade;
    private Long padraoDesempenho;
}

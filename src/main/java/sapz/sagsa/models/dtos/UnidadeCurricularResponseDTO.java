package sapz.sagsa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeCurricularResponseDTO {

    private Long id;
    private String nome;
    private String sigla;
    private Integer cargaHoraria;
    private Long moduloId;
}
package sapz.sagsa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoAprendizagemResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String contexto;
    private String desafio;
    private Long unidadeCurricularId;
    private String unidadeCurricularNome;
}
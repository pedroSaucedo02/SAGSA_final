package sapz.sagsa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanoCursoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String versao;
    private Long cursoId;
    private String cursoNome;
}
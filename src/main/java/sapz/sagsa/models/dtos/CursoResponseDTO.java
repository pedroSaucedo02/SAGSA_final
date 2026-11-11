package sapz.sagsa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String descricao;
    private Integer cargaHoraria;
    private String areaTecnologicaNome;
}
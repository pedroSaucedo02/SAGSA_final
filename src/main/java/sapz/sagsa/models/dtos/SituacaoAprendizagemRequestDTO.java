package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoAprendizagemRequestDTO {

    @NotBlank(message = "O título da situação de aprendizagem é obrigatório.")
    private String titulo;

    private String descricao;

    private String contexto;

    private String desafio;

    @NotNull(message = "O ID da unidade curricular é obrigatório.")
    private Long unidadeCurricularId;
} 
    


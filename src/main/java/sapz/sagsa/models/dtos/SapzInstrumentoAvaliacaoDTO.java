package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SapzInstrumentoAvaliacaoDTO {

    private Long id;

    @NotBlank(message = "O nome do instrumento é obrigatório.")
    @Size(max = 100)
    private String nomeInstrumento;

    private String descricao;

    @Builder.Default
    private String tipoMetodo = "GRADUAL";

    @NotNull(message = "O ID da situação de aprendizagem é obrigatório.")
    private Long situacaoAprendizagemId;
}
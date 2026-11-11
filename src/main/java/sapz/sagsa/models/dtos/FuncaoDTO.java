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
public class FuncaoDTO {

    private Long id;

    @NotNull(message = "O número da função é obrigatório.")
    private Integer numero;

    @NotBlank(message = "A descrição da função é obrigatória.")
    private String descricao;

    @NotNull(message = "O ID do plano de curso é obrigatório.")
    private Long planoCursoId;
}
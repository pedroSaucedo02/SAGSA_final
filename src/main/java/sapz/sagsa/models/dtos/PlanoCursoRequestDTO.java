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
public class PlanoCursoRequestDTO {

    @NotBlank(message = "O título do plano de curso é obrigatório.")
    private String titulo;

    private String descricao;

    private String versao;

    @NotNull(message = "O ID do curso é obrigatório.")
    private Long cursoId;
}
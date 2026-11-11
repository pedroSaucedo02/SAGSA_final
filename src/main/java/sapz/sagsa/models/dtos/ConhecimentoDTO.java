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
public class ConhecimentoDTO {

    private Long id;

    @NotBlank(message = "O tópico é obrigatório.")
    @Size(max = 200)
    private String topico;

    private String detalhamento;

    @NotNull(message = "O ID da unidade curricular é obrigatório.")
    private Long unidadeCurricularId;
}
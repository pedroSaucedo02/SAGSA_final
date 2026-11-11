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
public class CapacidadeDTO {

    private Long id;

    @NotBlank(message = "O tipo da capacidade é obrigatório.")
    @Size(max = 30)
    private String tipo;

    @NotBlank(message = "O domínio da capacidade é obrigatório.")
    @Size(max = 30)
    private String dominio;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O ID da unidade curricular é obrigatório.")
    private Long unidadeCurricularId;
}
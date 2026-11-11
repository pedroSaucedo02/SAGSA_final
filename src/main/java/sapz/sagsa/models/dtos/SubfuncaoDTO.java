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
public class SubfuncaoDTO {

    private Long id;

    @NotBlank(message = "O código da subfunção é obrigatório.")
    @Size(max = 20)
    private String codigoSubfuncao;

    @NotBlank(message = "A descrição da subfunção é obrigatória.")
    private String descricao;

    @NotNull(message = "O ID da função é obrigatório.")
    private Long funcaoId;
}
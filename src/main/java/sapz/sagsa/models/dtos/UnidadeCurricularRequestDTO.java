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
public class UnidadeCurricularRequestDTO {

    @NotBlank(message = "O nome da unidade curricular é obrigatório.")
    private String nome;

    private String sigla;

    @NotNull(message = "A carga horária é obrigatória.")
    private Integer cargaHoraria;

    @NotNull(message = "O ID do módulo é obrigatório.")
    private Long moduloId;
}
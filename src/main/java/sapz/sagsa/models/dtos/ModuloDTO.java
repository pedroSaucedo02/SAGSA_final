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
public class ModuloDTO {

    private Long id;

    @NotBlank(message = "O nome do módulo é obrigatório.")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O tipo do módulo é obrigatório.")
    @Size(max = 30)
    private String tipoModulo;

    @Builder.Default
    private Integer ordem = 1;

    @NotNull(message = "A carga horária do módulo é obrigatória.")
    private Integer cargaHoraria;

    @NotNull(message = "O ID do plano de curso é obrigatório.")
    private Long planoCursoId;
}
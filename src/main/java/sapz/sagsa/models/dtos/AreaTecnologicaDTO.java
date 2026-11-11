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
public class AreaTecnologicaDTO {

    private Long id;

    @NotBlank(message = "O nome da área tecnológica é obrigatório.")
    @Size(max = 100)
    private String nome;

    @NotNull(message = "O ID do eixo tecnológico é obrigatório.")
    private Long eixoTecnologicoId;
}
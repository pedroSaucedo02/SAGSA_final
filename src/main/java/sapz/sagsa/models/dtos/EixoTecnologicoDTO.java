package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EixoTecnologicoDTO {

    private Long id;

    @NotBlank(message = "O nome do eixo tecnológico é obrigatório.")
    @Size(max = 100)
    private String nome;
}
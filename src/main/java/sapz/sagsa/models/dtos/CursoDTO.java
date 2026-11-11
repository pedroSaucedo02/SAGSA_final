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
public class CursoDTO {

    private Long id;

    @NotBlank(message = "O nome do curso é obrigatório.")
    @Size(max = 150)
    private String nome;

    @NotBlank(message = "O tipo de curso é obrigatório.")
    @Size(max = 50)
    private String tipoCurso;

    private Long areaTecnologicaId;
    private String areaTecnologicaNome;

    @Builder.Default
    private Boolean ativo = true;
}
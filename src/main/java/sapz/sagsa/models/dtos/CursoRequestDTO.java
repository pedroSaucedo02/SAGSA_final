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
public class CursoRequestDTO {

    @NotBlank(message = "O nome do curso é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String nome;

    @NotBlank(message = "O código do curso é obrigatório.")
    private String codigo;

    private String descricao;

    @NotNull(message = "A carga horária é obrigatória.")
    private Integer cargaHoraria;

    @NotNull(message = "O ID da área tecnológica é obrigatório.")
    private Long areaTecnologicaId;
}
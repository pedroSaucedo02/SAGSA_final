package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SapzCompartilhamentoDTO {

    private Long id;

    @NotNull(message = "O ID da situação de aprendizagem é obrigatório.")
    private Long situacaoAprendizagemId;

    private Long escolaId;
    private Long cursoId;
    private Long usuarioDestinatarioId;

    @NotBlank(message = "O tipo de compartilhamento é obrigatório.")
    @Size(max = 30)
    private String tipoCompartilhamento;

    private LocalDateTime dataCompartilhamento;
}
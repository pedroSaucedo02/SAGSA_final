package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {

    private Long id;

    @NotBlank(message = "O código/nome da turma é obrigatório.")
    private String codigo;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @NotNull(message = "O ID do curso é obrigatório.")
    private Long cursoId;

    @NotNull(message = "O ID da escola é obrigatório.")
    private Long escolaId;
}
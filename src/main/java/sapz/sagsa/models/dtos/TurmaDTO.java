package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O código da turma é obrigatório.")
    @Size(max = 50)
    private String codigoTurma;

    @NotNull(message = "O ano é obrigatório.")
    private Integer ano;

    @NotNull(message = "O semestre é obrigatório.")
    private Integer semestre;

    @NotNull(message = "A data de início é obrigatória.")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    private LocalDate dataFim;

    @NotNull(message = "O ID da escola é obrigatório.")
    private Long escolaId;

    @NotNull(message = "O ID do plano de curso é obrigatório.")
    private Long planoCursoId;

    private Long coordenadorId;

    @Builder.Default
    private Boolean ativo = true;
}
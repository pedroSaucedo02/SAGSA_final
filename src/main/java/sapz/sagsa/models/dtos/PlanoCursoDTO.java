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
public class PlanoCursoDTO {

    private Long id;

    @NotBlank(message = "O código do plano é obrigatório.")
    @Size(max = 50)
    private String codigoPlano;

    @NotBlank(message = "A versão do plano é obrigatória.")
    @Size(max = 20)
    private String versao;

    @NotNull(message = "A carga horária total é obrigatória.")
    private Integer cargaHorariaTotal;

    private LocalDate dataAprovacao;

    @NotNull(message = "O ID do curso é obrigatório.")
    private Long cursoId;
    private String cursoNome;

    @Builder.Default
    private Boolean ativo = true;
}
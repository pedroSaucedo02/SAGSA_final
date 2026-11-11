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
public class SituacaoAprendizagemDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 200)
    private String titulo;

    @NotNull(message = "O ID da unidade curricular é obrigatório.")
    private Long unidadeCurricularId;

    @NotNull(message = "O ID do instrutor é obrigatório.")
    private Long instrutorId;

    private Long turmaId;

    @NotBlank(message = "O tipo de estratégia é obrigatório.")
    @Size(max = 50)
    private String tipoEstrategia;

    @NotBlank(message = "A contextualização é obrigatória.")
    private String contextualizacao;

    @NotBlank(message = "O desafio é obrigatório.")
    private String desafio;

    @NotBlank(message = "Os resultados esperados são obrigatórios.")
    private String resultadosEsperados;

    @NotNull(message = "A carga horária da SA é obrigatória.")
    private Integer cargaHorariaSa;

    @Builder.Default
    private Boolean compartilhada = false;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscolaDTO {

    private Long id;

    @NotBlank(message = "O nome da escola é obrigatório.")
    private String nome;

    private String codigo;

    private String cidade;

    private String uf;
}
package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;

    @NotBlank(message = "O nome do aluno é obrigatório.")
    private String nome;

    @NotBlank(message = "A matrícula do aluno é obrigatória.")
    private String matricula;

    @Email(message = "O email informado é inválido.")
    private String email;
}
package sapz.sagsa.models.dtos;

import jakarta.validation.constraints.Email;
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
public class AlunoDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(max = 14)
    private String cpf;

    @Email
    @Size(max = 150)
    private String email;

    @NotBlank(message = "A matrícula é obrigatória.")
    @Size(max = 50)
    private String matricula;
}
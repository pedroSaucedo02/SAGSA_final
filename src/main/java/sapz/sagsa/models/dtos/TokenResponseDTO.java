package sapz.sagsa.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDTO {

    private String token;

    @Builder.Default
    private String tipo = "Bearer";

    private String email;
    private String perfil;
}
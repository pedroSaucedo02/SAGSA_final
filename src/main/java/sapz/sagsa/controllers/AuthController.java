package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.models.dtos.LoginRequestDTO;
import sapz.sagsa.models.dtos.TokenResponseDTO;
import sapz.sagsa.repositories.UsuarioRepository;
import sapz.sagsa.security.TokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        authenticationManager.authenticate(usernamePassword);

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        String token = tokenService.gerarToken(usuario);

        TokenResponseDTO response = TokenResponseDTO.builder()
                .token(token)
                .tipo("Bearer")
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil().getNome())
                .build();

        return ResponseEntity.ok(response);
    }
}
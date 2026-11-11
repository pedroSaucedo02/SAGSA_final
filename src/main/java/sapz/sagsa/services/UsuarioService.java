package sapz.sagsa.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sapz.sagsa.models.Perfil;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.models.dtos.UsuarioCreateDTO;
import sapz.sagsa.models.dtos.UsuarioUpdateDTO;
import sapz.sagsa.repositories.PerfilRepository;
import sapz.sagsa.repositories.UsuarioRepository;

@Service
public class UsuarioService extends CrudService<Usuario> {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            PerfilRepository perfilRepository,
            PasswordEncoder passwordEncoder) {
        super(repository, "Usuário");
        this.usuarioRepository = repository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario create(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(findPerfil(dto.getPerfilId()));
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = findById(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setPerfil(findPerfil(dto.getPerfilId()));
        usuario.setAtivo(dto.getAtivo());
        if (dto.getSenha() != null) {
            usuario.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    private Perfil findPerfil(Long id) {
        return perfilRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado. Id: " + id));
    }
}

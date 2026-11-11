package sapz.sagsa.services;

import org.springframework.stereotype.Service;
import sapz.sagsa.models.Perfil;
import sapz.sagsa.repositories.PerfilRepository;

@Service
public class PerfilService extends CrudService<Perfil> {
    public PerfilService(PerfilRepository repository) {
        super(repository, "Perfil");
    }
}

package sapz.sagsa.services;

import org.springframework.stereotype.Service;
import sapz.sagsa.models.Curso;
import sapz.sagsa.repositories.CursoRepository;

@Service
public class CursoService extends CrudService<Curso> {
    public CursoService(CursoRepository repository) {
        super(repository, "Curso");
    }
}

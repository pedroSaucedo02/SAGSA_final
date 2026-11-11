package sapz.sagsa.services;

import org.springframework.stereotype.Service;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.repositories.PlanoCursoRepository;

@Service
public class PlanoCursoService extends CrudService<PlanoCurso> {
    public PlanoCursoService(PlanoCursoRepository repository) {
        super(repository, "Plano de curso");
    }
}

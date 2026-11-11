package sapz.sagsa.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.SituacaoAprendizagem;
import sapz.sagsa.repositories.SituacaoAprendizagemRepository;

import java.util.List;

@Service
public class SituacaoAprendizagemService extends CrudService<SituacaoAprendizagem> {
    private final SituacaoAprendizagemRepository repository;

    public SituacaoAprendizagemService(SituacaoAprendizagemRepository repository) {
        super(repository, "Situação de aprendizagem");
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagem> findByInstrutor(Long instrutorId) {
        return repository.findByInstrutor_Id(instrutorId);
    }
}

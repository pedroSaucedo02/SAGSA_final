package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.TurmaAluno;
import sapz.sagsa.models.TurmaAlunoId;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, TurmaAlunoId> {
}

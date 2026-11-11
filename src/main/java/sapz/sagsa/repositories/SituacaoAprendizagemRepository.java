package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SituacaoAprendizagem;

import java.util.List;

@Repository
public interface SituacaoAprendizagemRepository extends JpaRepository<SituacaoAprendizagem, Long> {
    List<SituacaoAprendizagem> findByInstrutor_Id(Long instrutorId);
}

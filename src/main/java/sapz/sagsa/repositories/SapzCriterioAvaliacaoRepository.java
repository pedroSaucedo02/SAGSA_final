package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzCriterioAvaliacao;

@Repository
public interface SapzCriterioAvaliacaoRepository extends JpaRepository<SapzCriterioAvaliacao, Long> {
}

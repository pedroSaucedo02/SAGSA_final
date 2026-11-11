package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzInstrumentoAvaliacao;

@Repository
public interface SapzInstrumentoAvaliacaoRepository extends JpaRepository<SapzInstrumentoAvaliacao, Long> {
}

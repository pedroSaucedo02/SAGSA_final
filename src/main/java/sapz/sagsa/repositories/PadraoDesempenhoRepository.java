package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.PadraoDesempenho;

@Repository
public interface PadraoDesempenhoRepository extends JpaRepository<PadraoDesempenho, Long> {
}

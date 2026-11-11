package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.CapacidadePadraoDesempenho;
import sapz.sagsa.models.CapacidadePadraoDesempenhoId;

@Repository
public interface CapacidadePadraoDesempenhoRepository extends JpaRepository<CapacidadePadraoDesempenho, CapacidadePadraoDesempenhoId> {
}

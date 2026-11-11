package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzConhecimento;
import sapz.sagsa.models.SapzConhecimentoId;

@Repository
public interface SapzConhecimentoRepository extends JpaRepository<SapzConhecimento, SapzConhecimentoId> {
}

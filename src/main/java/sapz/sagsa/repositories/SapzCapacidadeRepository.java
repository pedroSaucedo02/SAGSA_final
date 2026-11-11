package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzCapacidade;
import sapz.sagsa.models.SapzCapacidadeId;

@Repository
public interface SapzCapacidadeRepository extends JpaRepository<SapzCapacidade, SapzCapacidadeId> {
}

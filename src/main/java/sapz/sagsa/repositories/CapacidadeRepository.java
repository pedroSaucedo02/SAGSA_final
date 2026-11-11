package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Capacidade;

@Repository
public interface CapacidadeRepository extends JpaRepository<Capacidade, Long> {
}

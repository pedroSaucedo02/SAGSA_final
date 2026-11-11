package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {
}

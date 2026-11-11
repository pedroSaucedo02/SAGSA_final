package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzEstrategiaEnsino;

@Repository
public interface SapzEstrategiaEnsinoRepository extends JpaRepository<SapzEstrategiaEnsino, Long> {
}

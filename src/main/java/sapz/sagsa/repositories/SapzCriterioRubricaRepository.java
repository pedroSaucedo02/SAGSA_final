package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzCriterioRubrica;

@Repository
public interface SapzCriterioRubricaRepository extends JpaRepository<SapzCriterioRubrica, Long> {
}

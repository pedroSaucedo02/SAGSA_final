package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.TurmaDocenteUc;
import sapz.sagsa.models.TurmaDocenteUcId;

@Repository
public interface TurmaDocenteUcRepository extends JpaRepository<TurmaDocenteUc, TurmaDocenteUcId> {
}

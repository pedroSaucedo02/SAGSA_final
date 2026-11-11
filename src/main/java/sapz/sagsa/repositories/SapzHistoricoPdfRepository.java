package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzHistoricoPdf;

@Repository
public interface SapzHistoricoPdfRepository extends JpaRepository<SapzHistoricoPdf, Long> {
}

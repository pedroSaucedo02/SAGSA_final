package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
}

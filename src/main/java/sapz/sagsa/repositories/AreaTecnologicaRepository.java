package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.AreaTecnologica;

@Repository
public interface AreaTecnologicaRepository extends JpaRepository<AreaTecnologica, Long> {
}

package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.EixoTecnologico;

@Repository
public interface EixoTecnologicoRepository extends JpaRepository<EixoTecnologico, Long> {
}

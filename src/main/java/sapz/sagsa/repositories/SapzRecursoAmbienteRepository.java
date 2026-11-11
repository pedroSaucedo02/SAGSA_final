package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzRecursoAmbiente;

@Repository
public interface SapzRecursoAmbienteRepository extends JpaRepository<SapzRecursoAmbiente, Long> {
}

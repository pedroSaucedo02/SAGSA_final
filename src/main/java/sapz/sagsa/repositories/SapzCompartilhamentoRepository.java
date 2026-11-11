package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.SapzCompartilhamento;

@Repository
public interface SapzCompartilhamentoRepository extends JpaRepository<SapzCompartilhamento, Long> {
}

package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Conhecimento;

@Repository
public interface ConhecimentoRepository extends JpaRepository<Conhecimento, Long> {
}

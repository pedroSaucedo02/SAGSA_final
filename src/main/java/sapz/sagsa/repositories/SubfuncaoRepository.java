package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Subfuncao;

@Repository
public interface SubfuncaoRepository extends JpaRepository<Subfuncao, Long> {
}

package sapz.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapz.sagsa.models.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
}

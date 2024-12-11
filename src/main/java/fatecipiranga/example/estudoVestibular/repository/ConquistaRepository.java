package fatecipiranga.example.estudoVestibular.repository;

 
import fatecipiranga.example.estudoVestibular.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    @Query(value = "SELECT * FROM conquista WHERE vestibular_id=?1 AND (nome=?2 OR descricao=?3)", nativeQuery = true)
    Optional<Conquista> procurarConquista(Long vestibularId, String nome, String descricao);

    @Query(value = "SELECT * FROM conquista WHERE vestibular_id=?1", nativeQuery = true)
    Optional<List<Conquista>> procurarConquistarPorVestibular(Long vestibularId);
}
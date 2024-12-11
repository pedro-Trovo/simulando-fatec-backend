package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.model.Prova;
import fatecipiranga.example.estudoVestibular.model.ProvaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProvaRepository extends JpaRepository<Prova, ProvaId> {
  @Query(value = "SELECT * FROM prova WHERE vestibular_id=?1 AND ano=?2 AND semestre=?3", nativeQuery = true)
  Optional<Prova> procurarProva(Long vestibularId, Integer ano, Integer semestre);

  @Query(value = "SELECT * FROM prova WHERE vestibular_id=?1", nativeQuery = true)
  Optional<List<Prova>> procurarProvaPorVestibular(Long vestibularId);
}

package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.Questao;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
  @Query(value = "SELECT * FROM questao WHERE enunciado=?1 AND pergunta=?2", nativeQuery = true)
  Optional<Questao> procurarQuestao(String enunciado, String pergunta);

  @Query(value = "SELECT * FROM questao WHERE vestibular_id=?1 AND ano=?2 AND semestre=?3", nativeQuery = true)
  Optional<List<Questao>> procurarQuestoesPorProva(Long vestibularId, Integer ano, Integer semestre);

  @Query(value = "SELECT * FROM questao WHERE vestibular_id = ?1", nativeQuery = true)
  Optional<List<Questao>> procurarQuestoesPorVestibular(Long vestibularId);
}

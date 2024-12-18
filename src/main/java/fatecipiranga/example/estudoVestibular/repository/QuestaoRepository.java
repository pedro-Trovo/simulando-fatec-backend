package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.Questao;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
  @Query(value = """
            SELECT * 
            FROM questao 
            WHERE enunciado = :enunciado 
              AND pergunta = :pergunta
         """, nativeQuery = true)
  Optional<Questao> procurarQuestao(
          @Param("enunciado") String enunciado,
          @Param("pergunta") String pergunta
  );

  @Query(value = """
            SELECT *
            FROM questao
            WHERE vestibular_id = :vestibularId
              AND ano = :ano
              AND semestre = :semestre
          """, nativeQuery = true)
  Optional<List<Questao>> procurarQuestoesPorProva(
          @Param("vestibularId") Long vestibularId,
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre
  );

  @Query(value = "SELECT * FROM questao WHERE vestibular_id = :vestibularId", nativeQuery = true)
  Optional<List<Questao>> procurarQuestoesPorVestibular(
          @Param("vestibularId") Long vestibularId
  );
}
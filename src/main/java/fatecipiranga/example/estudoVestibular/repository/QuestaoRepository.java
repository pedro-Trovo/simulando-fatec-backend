package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.Questao;


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

  @Query(value = " SELECT * FROM questao WHERE ano = :ano AND semestre = :semestre ", nativeQuery = true)
  Optional<List<Questao>> procurarQuestoesPorProva(
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre
  );

}
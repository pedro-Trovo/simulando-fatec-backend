package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QuestaoResolvidaRepository extends JpaRepository<QuestaoResolvida, Long> {
  @Query(value = """
            SELECT *
            FROM questao_resolvida
            WHERE prova_efetuada_id = :provaEfetuadaId
              AND questao_id = :questaoId
          """, nativeQuery = true)
  Optional<QuestaoResolvida> procurarQuestaoResolvida(
          @Param("provaEfetuadaId") Long provaEfetuadaId,
          @Param("questaoId") Long questaoId
  );

  @Modifying
  @Transactional
  @Query(value = """
            UPDATE questao_resolvida
            SET acertou = :acertou,
                letra_escolhida = :letraEscolhida,
                data = :data
            WHERE prova_efetuada_id = :provaEfetuadaId
              AND questao_id = :questaoId
          """, nativeQuery = true)
  int alterarQuestaoResolvida(
          @Param("provaEfetuadaId") Long provaEfetuadaId,
          @Param("questaoId") Long questaoId,
          @Param("acertou") Boolean acertou,
          @Param("letraEscolhida") String letraEscolhida,
          @Param("data") LocalDate data
  );

  @Query(value = """
            SELECT qr.*
            FROM questao_resolvida qr
            JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
            WHERE pe.aluno_id = :alunoId
          """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAluno(
          @Param("alunoId") Long alunoId
  );

  @Query(value = """
            SELECT qr.*
            FROM questao_resolvida qr
            JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
            WHERE pe.aluno_id = :alunoId
              AND pe.vestibular_id = :vestibularId
              AND pe.ano = :ano
              AND pe.semestre = :semestre
          """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorProva(
          @Param("alunoId") Long alunoId,
          @Param("vestibularId") Long vestibularId,
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre
  );

  @Query(value = """
            SELECT qr.* 
            FROM questao_resolvida qr
            JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
            WHERE pe.aluno_id = :alunoId 
              AND pe.vestibular_id = vestibularId
         """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorVestibular(
          @Param("alunoId") Long alunoId,
          @Param("vestibularId") Long vestibularId
  );
}
package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QuestaoResolvidaRepository extends JpaRepository<QuestaoResolvida, Long> {
  @Query(value = "SELECT * FROM questao_resolvida WHERE aluno_id=?1 AND questao_id=?2", nativeQuery = true)
  Optional<QuestaoResolvida> procurarQuestaoResolvida(Long alunoId, Long questaoId);

  @Modifying
  @Transactional
  @Query(value = """
            UPDATE questao_resolvida
            SET acertou = ?3
            SET letra_escolhida = ?4
            SET data = ?5
            WHERE aluno_id=?1 AND questao_id=?2
          """, nativeQuery = true)
  int alterarQuestaoResolvida(Long alunoId, Long questaoId, Boolean acertou, String letraEscolhida, LocalDate data);

  @Query(value = "SELECT * FROM questao_resolvida WHERE aluno_id=?1", nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAluno(Long alunoId);

  @Query(value = """
            SELECT qr.*
            FROM questao_resolvida qr
            JOIN questao q ON qr.questao_id = q.id
            WHERE qr.aluno_id = ?1 AND q.vestibular_id=?2 AND q.ano=?3 AND q.semestre=?4
          """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorProva(Long alunoId, Long vestibularId, Integer ano, Integer semestre);

  @Query(value = """
            SELECT qr.* 
            FROM questao_resolvida qr
            JOIN questao q ON qr.questao_id = q.id
            WHERE qr.aluno_id = ?1 AND q.vestibular_id=?2
          """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorVestibular(Long alunoId, Long vestibularId);
}
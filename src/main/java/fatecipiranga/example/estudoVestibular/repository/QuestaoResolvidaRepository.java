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
  @Query(value = "SELECT * FROM questao_resolvida WHERE prova_efetuada_id=?1 AND questao_id=?2", nativeQuery = true)
  Optional<QuestaoResolvida> procurarQuestaoResolvida(Long provaEfetuadaId, Long questaoId);

  @Modifying
  @Transactional
  @Query(value = """
    UPDATE questao_resolvida
    SET acertou = ?3,
        letra_escolhida = ?4,
        data = ?5
    WHERE prova_efetuada_id=?1 AND questao_id=?2
  """, nativeQuery = true)
  int alterarQuestaoResolvida(Long provaEfetuadaId, Long questaoId, Boolean acertou, String letraEscolhida, LocalDate data);

  @Query(value = """
    SELECT qr.*
    FROM questao_resolvida qr
    JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
    WHERE pe.aluno_id=?1
    """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAluno(Long alunoId);

  @Query(value = """
    SELECT qr.*
    FROM questao_resolvida qr
    JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
    WHERE pe.aluno_id = ?1 AND pe.vestibular_id=?2 AND pe.ano=?3 AND pe.semestre=?4
  """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorProva(Long alunoId, Long vestibularId, Integer ano, Integer semestre);

  @Query(value = """
    SELECT qr.* 
    FROM questao_resolvida qr
    JOIN prova_efetuada pe ON qr.prova_efetuada_id = pe.id
    WHERE pe.aluno_id = ?1  AND pe.vestibular_id=?2
  """, nativeQuery = true)
  Optional<List<QuestaoResolvida>> procurarQuestoesResolvidasPorAlunoPorVestibular(Long alunoId, Long vestibularId);
}
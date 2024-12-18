package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.ProvaEfetuada;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProvaEfetuadaRepository extends JpaRepository<ProvaEfetuada, Long> {
  @Query(value = """
            SELECT * FROM prova_efetuada
            WHERE aluno_id = :alunoId
              AND vestibular_id = :vestibularId
              AND ano = :ano
              AND semestre = :semestre
          """, nativeQuery = true)
  Optional<ProvaEfetuada> procurarProvaEfetuada(
          @Param("alunoId") Long alunoId,
          @Param("vestibularId") Long vestibularId,
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre
  );

  @Modifying
  @Transactional
  @Query(value = """
            UPDATE prova_efetuada
            SET data = :data,
                situacao = :situacao,
                tempo_acumulado_segundos = :tempoAcumuladoSegundos
            WHERE id = :provaEfetuadaId
          """, nativeQuery = true)
  int alterarProvaEfetuada(
          @Param("provaEfetuadaId") Long provaEfetuadaId,
          @Param("data") LocalDate data,
          @Param("situacao") String situacao,
          @Param("tempoAcumuladoSegundos") Integer tempoAcumuladoSegundos
  );

  @Query(value = "SELECT * FROM prova_efetuada WHERE aluno_id = :alunoId", nativeQuery = true)
  Optional<List<ProvaEfetuada>> procurarProvasEfetuadasPorAluno(
          @Param("alunoId") Long alunoId
  );

  @Query(value = """
            SELECT *
            FROM prova_efetuada
            WHERE aluno_id = :alunoId
              AND vestibular_id = :vestibularId
          """, nativeQuery = true)
  Optional<List<ProvaEfetuada>> procurarProvasEfetuadasPorAlunoPorVestibular(
          @Param("alunoId") Long alunoId,
          @Param("vestibularId") Long vestibularId
  );
}

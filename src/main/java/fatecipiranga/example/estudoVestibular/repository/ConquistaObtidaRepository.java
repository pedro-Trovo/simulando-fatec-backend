package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.ConquistaObtida;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConquistaObtidaRepository extends JpaRepository<ConquistaObtida, Long> {
  @Query(value = """
            SELECT * 
            FROM conquista_obtida 
            WHERE aluno_id = :alunoId 
              AND conquista_id = :conquistaId
          """, nativeQuery = true)
  Optional<ConquistaObtida> procurarConquistaObtida(
          @Param("alunoId") Long alunoId,
          @Param("conquistaId") Long conquistaId
  );

  @Query(value = "SELECT * FROM conquista_obtida WHERE aluno_id = :alunoId", nativeQuery = true)
  Optional<List<ConquistaObtida>> procurarConquistasObtidasPorAluno(
          @Param("alunoId") Long alunoId
  );

  @Query(value = """
            SELECT co.* 
            FROM conquista_obtida co
            JOIN conquista c ON co.conquista_id = c.id
            WHERE co.aluno_id = :alunoId 
              AND c.vestibular_id = :vestibularId
          """, nativeQuery = true)
  Optional<List<ConquistaObtida>> procurarConquistasObtidasPorAlunoPorVestibular(
          @Param("alunoId") Long alunoId,
          @Param("vestibularId") Long vestibularId
  );
}
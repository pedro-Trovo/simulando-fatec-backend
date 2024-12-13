package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.ConquistaObtida;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConquistaObtidaRepository extends JpaRepository<ConquistaObtida, Long> {
  @Query(value = "SELECT * FROM conquista_obtida WHERE aluno_id=?1 AND conquista_id=?2", nativeQuery = true)
  Optional<ConquistaObtida> procurarConquistaObtida(Long alunoId, Long conquistaId);

  @Query(value = "SELECT * FROM conquista_obtida WHERE aluno_id=?1", nativeQuery = true)
  Optional<List<ConquistaObtida>> procurarConquistasObtidasPorAluno(Long alunoId);

  @Query(value = """
    SELECT co.* 
    FROM conquista_obtida co
    JOIN conquista c ON co.conquista_id = c.id
    WHERE co.aluno_id=?1 AND c.vestibular_id=?2
  """, nativeQuery = true)
  Optional<List<ConquistaObtida>> procurarConquistasObtidasPorAlunoPorVestibular(Long alunoId, Long vestibularId);
}
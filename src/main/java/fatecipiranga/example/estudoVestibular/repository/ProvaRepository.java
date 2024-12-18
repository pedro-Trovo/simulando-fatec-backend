package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.model.Prova;
import fatecipiranga.example.estudoVestibular.model.ProvaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProvaRepository extends JpaRepository<Prova, ProvaId> {
  @Query(value = """
            SELECT *
            FROM prova
            WHERE vestibular_id = :vestibularId
              AND ano = :ano
              AND semestre = :semestre
          """, nativeQuery = true)
  Optional<Prova> procurarProva(
          @Param("vestibularId") Long vestibularId,
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre);

  @Query(value = "SELECT * FROM prova WHERE vestibular_id = :vestibularId", nativeQuery = true)
  Optional<List<Prova>> procurarProvaPorVestibular(
          @Param("vestibularId") Long vestibularId
  );
}
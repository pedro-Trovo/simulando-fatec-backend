package fatecipiranga.example.estudoVestibular.repository;


import fatecipiranga.example.estudoVestibular.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
  @Query(value = """
            SELECT *
            FROM conquista
            WHERE vestibular_id = :vestibularId
              AND (nome = :nome OR descricao = :descricao)
          """, nativeQuery = true)
  Optional<Conquista> procurarConquista(
          @Param("vestibularId") Long vestibularId,
          @Param("nome") String nome,
          @Param("descricao") String descricao
  );

  @Query(value = "SELECT * FROM conquista WHERE vestibular_id = :vestibularId", nativeQuery = true)
  Optional<List<Conquista>> procurarConquistasPorVestibular(
          @Param("vestibularId") Long vestibularId
  );
}
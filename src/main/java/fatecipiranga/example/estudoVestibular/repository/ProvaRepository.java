package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Prova;
import fatecipiranga.example.estudoVestibular.model.ProvaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProvaRepository extends JpaRepository<Prova, ProvaId> {
  @Query(value = """
            SELECT *
            FROM prova
            WHERE 
              ano = :ano
              AND semestre = :semestre
          """, nativeQuery = true)
  Optional<Prova> procurarProva(
          @Param("ano") Integer ano,
          @Param("semestre") Integer semestre);

}
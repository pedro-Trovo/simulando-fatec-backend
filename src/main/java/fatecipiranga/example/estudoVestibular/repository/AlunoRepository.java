package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  @Query(value = "SELECT * FROM aluno WHERE email = :email", nativeQuery = true)
  Optional<Aluno> procurarLogin(
          @Param("email") String email
  );
}
package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "SELECT COUNT(*) FROM aluno WHERE email = ?1", nativeQuery = true)
    Long checarEmailExiste(String email);

    @Query(value = "SELECT * FROM aluno WHERE email = ?1", nativeQuery = true)
    Optional<Aluno> procurarLogin(String email);
}

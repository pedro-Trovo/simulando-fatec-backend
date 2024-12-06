package fatecipiranga.example.estudoVestibular.repository;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(value = "SELECT COUNT(*) > 0 FROM aluno WHERE email=?1", nativeQuery = true)
    boolean checarEmailExiste(String email);
}

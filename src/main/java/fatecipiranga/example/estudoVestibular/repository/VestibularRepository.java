package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.Vestibular;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VestibularRepository extends JpaRepository<Vestibular, Long> {
    @Query(value = "SELECT * FROM vestibular WHERE nome = :nome", nativeQuery = true)
    Optional<Vestibular> procurarVestibular(
            @Param("nome") String nome
    );
}
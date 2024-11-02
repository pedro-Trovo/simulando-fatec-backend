package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.Vestibular;


import org.springframework.data.jpa.repository.JpaRepository;

public interface VestibularRepository extends JpaRepository<Vestibular, Long> {
    
}

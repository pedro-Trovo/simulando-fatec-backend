package fatecipiranga.example.estudoVestibular.repository;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;

 
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoResolvidaRepository extends JpaRepository<QuestaoResolvida, Long> {
    
}


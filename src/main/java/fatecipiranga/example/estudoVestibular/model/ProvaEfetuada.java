package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ProvaEfetuada {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "aluno_id", nullable = false)
  @JsonIgnoreProperties({"conquistasObtidas", "provasEfetuadas"})
  private Aluno aluno;

  @OneToMany(mappedBy = "provaEfetuada", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestaoResolvida> questoesResolvidas = new ArrayList<>();
}

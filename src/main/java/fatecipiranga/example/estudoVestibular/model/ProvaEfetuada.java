package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
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

  @ManyToOne
  @JoinColumns({
          @JoinColumn(name="vestibular_id", referencedColumnName="vestibular_id"),
          @JoinColumn(name="ano", referencedColumnName="ano"),
          @JoinColumn(name="semestre", referencedColumnName="semestre")
  })
  @JsonIgnoreProperties("questoes")
  private Prova prova;

  @OneToMany(mappedBy = "provaEfetuada", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestaoResolvida> questoesResolvidas = new ArrayList<>();

  @Temporal(TemporalType.DATE)
  @Column(nullable = false) // A coluna não pode ser "Null"
  private LocalDate data = LocalDate.now();

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String situacao;
}

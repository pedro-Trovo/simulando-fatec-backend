package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="prova_efetuada")
public class ProvaEfetuada {

  // Chave primária com Auto Increment de 1 em 1
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumn(name = "aluno_id", nullable = false)
  @JsonIgnoreProperties({"conquistasObtidas", "provasEfetuadas"})
  private Aluno aluno;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumns({
          @JoinColumn(name="ano", referencedColumnName="ano"),
          @JoinColumn(name="semestre", referencedColumnName="semestre")
  })
  @JsonIgnoreProperties("questoes")
  private Prova prova;

  @OneToMany(mappedBy = "provaEfetuada", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestaoResolvida> questoesResolvidas = new ArrayList<>();

  @Temporal(TemporalType.DATE)
  @Column(name = "data", nullable = false) // A coluna não pode ser "Null"
  private LocalDate data = LocalDate.now();

  // A coluna não pode ser "Null"
  @Column(name = "situacao", nullable = false)
  private String situacao = "Em Andamento";

  // A coluna não pode ser "Null"
  @Column(name = "tempo_acumulado_segundos", nullable = false)
  private Integer tempoAcumuladoSegundos;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Prova getProva() {
    return prova;
  }

  public void setProva(Prova prova) {
    this.prova = prova;
  }

  public List<QuestaoResolvida> getQuestoesResolvidas() {
    return questoesResolvidas;
  }

  public void setQuestoesResolvidas(List<QuestaoResolvida> questoesResolvidas) {
    this.questoesResolvidas = questoesResolvidas;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }

  public Integer getTempoAcumuladoSegundos() {
    return tempoAcumuladoSegundos;
  }

  public void setTempoAcumuladoSegundos(Integer tempoAcumuladoSegundos) {
    this.tempoAcumuladoSegundos = tempoAcumuladoSegundos;
  }
}
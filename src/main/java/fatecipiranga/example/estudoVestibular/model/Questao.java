package fatecipiranga.example.estudoVestibular.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="questao")
public class Questao {

  // Chave primária com Auto Increment de 1 em 1
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // A coluna não pode ser "Null"
  @Column(name = "num_questao", nullable = false)
  private int numQuestao;

  // A coluna não pode ser "Null"
  @Column(name = "disciplina", nullable = false)
  private String disciplina;

  @Column(name = "enunciado", length = 4000)
  private String enunciado;

  // A coluna não pode ser "Null"
  @Column(name = "pergunta", length = 2000, nullable = false)
  private String pergunta;

  @ElementCollection
  @Column(name = "imgs")
  private List<String> imgs;

  // A coluna não pode ser "Null"
  @Column(name = "gabarito", nullable = false)
  private String gabarito;

  @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Alternativa> alternativas;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumns({
          @JoinColumn(name="ano", referencedColumnName="ano"),
          @JoinColumn(name="semestre", referencedColumnName="semestre")
  })
  @JsonIgnoreProperties("questoes")
  private Prova prova;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getNumQuestao() {
    return numQuestao;
  }

  public void setNumQuestao(int numQuestao) {
    this.numQuestao = numQuestao;
  }

  public String getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
  }

  public String getEnunciado() {
    return enunciado;
  }

  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }

  public String getPergunta() {
    return pergunta;
  }

  public void setPergunta(String pergunta) {
    this.pergunta = pergunta;
  }

  public List<String> getImgs() {
    return imgs;
  }

  public void setImgs(List<String> imgs) {
    this.imgs = imgs;
  }

  public String getGabarito() {
    return gabarito;
  }

  public void setGabarito(String gabarito) {
    this.gabarito = gabarito;
  }

  public List<Alternativa> getAlternativas() {
    return alternativas;
  }

  public void setAlternativas(List<Alternativa> alternativas) {
    this.alternativas = alternativas;
  }

  public Prova getProva() {
    return prova;
  }

  public void setProva(Prova prova) {
    this.prova = prova;
  }
}
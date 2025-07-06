package fatecipiranga.example.estudoVestibular.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="questao_resolvida")
public class QuestaoResolvida {

  // Chave primária com Auto Increment de 1 em 1
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumn(name = "prova_efetuada_id", nullable = false)
  @JsonIgnoreProperties("questoesResolvidas")
  private ProvaEfetuada provaEfetuada;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumn(name = "questao_id", nullable = false)
  private Questao questao;

  // A coluna não pode ser "Null"
  @Column(name = "letra_escolhida", nullable = false)
  private String letraEscolhida;

  // A coluna não pode ser "Null"
  @Column(name = "acertou", nullable = false)
  private boolean acertou;

  @Temporal(TemporalType.DATE)
  @Column(name = "data", nullable = false) // A coluna não pode ser "Null"
  private LocalDate data = LocalDate.now();



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProvaEfetuada getProvaEfetuada() {
    return provaEfetuada;
  }

  public void setProvaEfetuada(ProvaEfetuada provaEfetuada) {
    this.provaEfetuada = provaEfetuada;
  }

  public Questao getQuestao() {
    return questao;
  }

  public void setQuestao(Questao questao) {
    this.questao = questao;
  }

  public String getLetraEscolhida() {
    return letraEscolhida;
  }

  public void setLetraEscolhida(String letraEscolhida) {
    this.letraEscolhida = letraEscolhida;
  }

  public boolean isAcertou() {
    return acertou;
  }

  public void setAcertou(boolean acertou) {
    this.acertou = acertou;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }
}
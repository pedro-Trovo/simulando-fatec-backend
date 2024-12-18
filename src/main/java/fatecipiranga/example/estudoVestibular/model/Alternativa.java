package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="alternativa")
public class Alternativa {

  // Chave primária com Auto Increment de 1 em 1
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Coluna para armazenar chave estrangeira
  @ManyToOne
  @JoinColumn(name = "questao_id", nullable = false)
  @JsonIgnore
  private Questao questao;

  // A coluna não pode ser "Null"
  @Column(name = "letra", nullable = false)
  private String letra;

  @Column(name = "texto")
  private String texto;

  @Column(name = "img_url")
  private String imgUrl;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Questao getQuestao() {
    return questao;
  }

  public void setQuestao(Questao questao) {
    this.questao = questao;
  }

  public String getLetra() {
    return letra;
  }

  public void setLetra(String letra) {
    this.letra = letra;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
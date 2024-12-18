package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="prova")
public class Prova {

  // Chave primária
  @EmbeddedId
  private ProvaId id;

  @OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Questao> questoes = new ArrayList<>();

  public Prova() {}

  public Prova(ProvaId provaId){
    this.id = provaId;
  }

  public ProvaId getId() {
    return id;
  }

  public void setId(ProvaId id) {
    this.id = id;
  }

  public List<Questao> getQuestoes() {
    return questoes;
  }

  public void setQuestoes(List<Questao> questoes) {
    this.questoes = questoes;
  }
}
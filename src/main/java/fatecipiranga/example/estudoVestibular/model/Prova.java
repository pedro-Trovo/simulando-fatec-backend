package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

@Entity
@Table(name="prova")
public class Prova {

  @EmbeddedId
  private ProvaId id;

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
}

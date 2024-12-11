package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ProvaId implements Serializable {
  @ManyToOne
  @JoinColumn(name = "vestibular_id", nullable = false)
  private Vestibular vestibular;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private int ano;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private int semestre;

  public ProvaId() {}

  public ProvaId(Vestibular vestibular, int ano, int semestre) {
    this.vestibular = vestibular;
    this.ano = ano;
    this.semestre = semestre;
  }

  public Vestibular getVestibular() {
    return vestibular;
  }

  public void setVestibular(Vestibular vestibular) {
    this.vestibular = vestibular;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public int getSemestre() {
    return semestre;
  }

  public void setSemestre(int semestre) {
    this.semestre = semestre;
  }
}

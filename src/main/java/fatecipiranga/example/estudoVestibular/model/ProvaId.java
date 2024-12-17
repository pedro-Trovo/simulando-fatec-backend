package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ProvaId implements Serializable {
  @ManyToOne
  @JoinColumn(name = "vestibular_id", nullable = false)
  @JsonIgnoreProperties({"conquistas", "provas"})
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true; // Verifica se é o mesmo objeto
    if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é do mesmo tipo

    ProvaId provaId = (ProvaId) o;

    // Compara os campos relevantes
    if (ano != provaId.ano) return false;
    if (semestre != provaId.semestre) return false;
    return vestibular != null ? vestibular.equals(provaId.vestibular) : provaId.vestibular == null;
  }

  @Override
  public int hashCode() {
    int result = vestibular != null ? vestibular.hashCode() : 0;
    result = 31 * result + ano;
    result = 31 * result + semestre;
    return result;
  }
}
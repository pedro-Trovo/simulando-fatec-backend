package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProvaId implements Serializable {

  // A coluna não pode ser "Null"
  @Column(name = "ano", nullable = false)
  private int ano;

  // A coluna não pode ser "Null"
  @Column(name = "semestre",nullable = false)
  private int semestre;

  public ProvaId() {}

  public ProvaId( int ano, int semestre) {
    this.ano = ano;
    this.semestre = semestre;
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
    return semestre == provaId.semestre;
  }

 @Override
  public int hashCode() {
    int result = Integer.hashCode(ano);
    result = 31 * result + Integer.hashCode(semestre);
    return result;
}

}
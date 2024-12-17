package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vestibular")
public class Vestibular {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String nome;

  @OneToMany(mappedBy = "vestibular", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Conquista> conquistas = new ArrayList<>();

  @OneToMany(mappedBy = "id.vestibular", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Prova> provas = new ArrayList<>();

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public String getNome() {
      return nome;
  }

  public void setNome(String nome) {
      this.nome = nome;
  }

  public List<Conquista> getConquistas() {
      return conquistas;
  }

  public void setConquistas(List<Conquista> conquistas) {
      this.conquistas = conquistas;
  }

  public List<Prova> getProvas() {
      return provas;
  }

  public void setProvas(List<Prova> provas) {
      this.provas = provas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true; // Verifica se é o mesmo objeto
    if (o == null || getClass() != o.getClass()) return false; // Verifica se o objeto é do mesmo tipo

    Vestibular vestibular = (Vestibular) o;

    // Compara o campo id
    return id != null ? id.equals(vestibular.id) : vestibular.id == null;
  }

  @Override
  public int hashCode() {
    // Calcula o hashCode com base no campo id
    return id != null ? id.hashCode() : 0;
  }
}
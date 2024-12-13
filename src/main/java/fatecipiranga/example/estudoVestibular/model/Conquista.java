package fatecipiranga.example.estudoVestibular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="conquista")
public class Conquista {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "vestibular_id", nullable = false)
  @JsonIgnoreProperties({"conquistas", "provas"})
  private Vestibular vestibular;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String nome;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String descricao;



  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public Vestibular getVestibular() {
      return vestibular;
  }

  public void setVestibular(Vestibular vestibular) {
      this.vestibular = vestibular;
  }

  public String getNome() {
      return nome;
  }

  public void setNome(String nome) {
      this.nome = nome;
  }

  public String getDescricao() {
      return descricao;
  }

  public void setDescricao(String descricao) {
      this.descricao = descricao;
  }
}
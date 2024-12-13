package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="aluno")
public class Aluno {

  // Define "id" como Primary Key e que possui AutoIncrement
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String nome;

  // A coluna não pode ser "Null"
  // Os valores da coluna devem ser únicos
  @Column(nullable = false, unique = true)
  private String email;

  // A coluna não pode ser "Null"
  @Column(nullable = false)
  private String senha;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ConquistaObtida> conquistasObtidas = new ArrayList<>();

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestaoResolvida> questoesResolvidas = new ArrayList<>();


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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<ConquistaObtida> getConquistasObtidas() {
    return conquistasObtidas;
  }

  public void setConquistasObtidas(List<ConquistaObtida> conquistasObtidas) {
    this.conquistasObtidas = conquistasObtidas;
  }

  public List<QuestaoResolvida> getQuestoesResolvidas() {
    return questoesResolvidas;
  }

  public void setQuestoesResolvidas(List<QuestaoResolvida> questoesResolvidas) {
    this.questoesResolvidas = questoesResolvidas;
  }
}
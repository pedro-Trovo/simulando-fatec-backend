package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="aluno")
public class Aluno {

  // Chave primária com Auto Increment de 1 em 1
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // A coluna não pode ser "Null"
  @Column(name = "nome", nullable = false)
  private String nome;

  // A coluna não pode ser "Null"
  // Os valores da coluna devem ser únicos
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  // A coluna não pode ser "Null"
  @Column(name = "senha", nullable = false)
  private String senha;

  @Column(name = "foto_perfil")
  private String fotoPerfil;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProvaEfetuada> provasEfetuadas = new ArrayList<>();


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

  public String getFotoPerfil() {
    return fotoPerfil;
  }

  public void setFotoPerfil(String fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
  }
  
  public List<ProvaEfetuada> getProvasEfetuadas() {
    return provasEfetuadas;
  }

  public void setProvasEfetuadas(List<ProvaEfetuada> provasEfetuadas) {
    this.provasEfetuadas = provasEfetuadas;
  }
}
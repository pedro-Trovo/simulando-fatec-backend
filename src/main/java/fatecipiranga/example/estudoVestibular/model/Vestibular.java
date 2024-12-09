package fatecipiranga.example.estudoVestibular.model;

import jakarta.persistence.*;

@Entity
public class Vestibular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A coluna não pode ser "Null"
    @Column(nullable = false)
    private String nome;

    // A coluna não pode ser "Null"
    @Column(nullable = false)
    private int ano;

    // A coluna não pode ser "Null"
    @Column(nullable = false)
    private int semestre;

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

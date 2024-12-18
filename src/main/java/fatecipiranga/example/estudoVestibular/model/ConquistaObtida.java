package fatecipiranga.example.estudoVestibular.model;


import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

@Entity
@Table(name="conquista_obtida")
public class ConquistaObtida {

    // Chave primária com Auto Increment de 1 em 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Coluna para armazenar chave estrangeira
    @ManyToOne
    @JoinColumn(name = "conquista_id", nullable = false)
    private Conquista conquista;

    // Coluna para armazenar chave estrangeira
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties({"conquistasObtidas", "provasEfetuadas"})
    private Aluno aluno;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private LocalDate data = LocalDate.now();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conquista getConquista() {
        return conquista;
    }

    public void setConquista(Conquista conquista) {
        this.conquista = conquista;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
package fatecipiranga.example.estudoVestibular.model;


import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class QuestaoResolvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties({"conquistasObtidas", "questoesResolvidas"})
    private Aluno aluno;   

    @ManyToOne
    @JoinColumn(name = "questao_id", nullable = false)
    private Questao questao;

    // A coluna não pode ser "Null"
    @Column(nullable = false)
    private boolean acertou;  

    @Temporal(TemporalType.DATE)
    @Column(nullable = false) // A coluna não pode ser "Null"
    private LocalDate data = LocalDate.now();

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public boolean isAcertou() {
        return acertou;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

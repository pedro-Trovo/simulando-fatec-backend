package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Questao;
import fatecipiranga.example.estudoVestibular.repository.QuestaoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestaoController {

    @Autowired
    QuestaoRepository bd;

    @PostMapping("/api/questao")
    public String gravar(@RequestBody Questao obj) {
        bd.save(obj);
        return "A questão " + obj.getNumQuestao() + " foi salva corretamente!";
    }

    @PutMapping("/api/questao")
    public String alterar(@RequestBody Questao obj) {
        bd.save(obj);
        return "A questão " + obj.getNumQuestao() + " foi alterada corretamente!";
    }

    @GetMapping("/api/questao/{codigo}")
    public Questao carregar(@PathVariable Long codigo) {
        Optional<Questao> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/questao/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/questoes")
    public List<Questao> listar() {
        return bd.findAll();
    }
}

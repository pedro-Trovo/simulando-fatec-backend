package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import fatecipiranga.example.estudoVestibular.repository.QuestaoResolvidaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestaoResolvidaController {

    @Autowired
    QuestaoResolvidaRepository bd;

    @PostMapping("/api/questao-resolvida")
    public String gravar(@RequestBody QuestaoResolvida obj) {
        bd.save(obj);
        return "A questão resolvida foi salva corretamente!";
    }

    @PutMapping("/api/questao-resolvida")
    public String alterar(@RequestBody QuestaoResolvida obj) {
        bd.save(obj);
        return "A questão resolvida foi alterada corretamente!";
    }

    @GetMapping("/api/questao-resolvida/{codigo}")
    public QuestaoResolvida carregar(@PathVariable Long codigo) {
        Optional<QuestaoResolvida> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/questao-resolvida/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/questoes-resolvidas")
    public List<QuestaoResolvida> listar() {
        return bd.findAll();
    }
}


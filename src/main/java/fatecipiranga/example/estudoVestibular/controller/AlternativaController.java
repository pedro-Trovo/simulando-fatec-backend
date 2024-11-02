package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Alternativa;
import fatecipiranga.example.estudoVestibular.repository.AlternativaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AlternativaController {

    @Autowired
    AlternativaRepository bd;

    @PostMapping("/api/alternativa")
    public String gravar(@RequestBody Alternativa obj) {
        bd.save(obj);
        return "A alternativa " + obj.getLetra() + " foi salva corretamente!";
    }

    @PutMapping("/api/alternativa")
    public String alterar(@RequestBody Alternativa obj) {
        bd.save(obj);
        return "A alternativa " + obj.getLetra() + " foi alterada corretamente!";
    }

    @GetMapping("/api/alternativa/{codigo}")
    public Alternativa carregar(@PathVariable Long codigo) {
        Optional<Alternativa> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/alternativa/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/alternativas")
    public List<Alternativa> listar() {
        return bd.findAll();
    }
}

package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.repository.ConquistaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ConquistaController {

    @Autowired
    ConquistaRepository bd;

    @PostMapping("/api/conquista")
    public String gravar(@RequestBody Conquista obj) {
        bd.save(obj);
        return "A conquista " + obj.getNome() + " foi salva corretamente!";
    }

    @PutMapping("/api/conquista")
    public String alterar(@RequestBody Conquista obj) {
        bd.save(obj);
        return "A conquista " + obj.getNome() + " foi alterada corretamente!";
    }

    @GetMapping("/api/conquista/{codigo}")
    public Conquista carregar(@PathVariable Long codigo) {
        Optional<Conquista> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/conquista/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/conquistas")
    public List<Conquista> listar() {
        return bd.findAll();
    }
}

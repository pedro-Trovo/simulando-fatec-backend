package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.ConquistaObtida;
import fatecipiranga.example.estudoVestibular.repository.ConquistaObtidaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ConquistaObtidaController {

    @Autowired
    ConquistaObtidaRepository bd;

    @PostMapping("/api/conquista-obtida")
    public String gravar(@RequestBody ConquistaObtida obj) {
        bd.save(obj);
        return "A conquista obtida foi salva corretamente!";
    }

    @PutMapping("/api/conquista-obtida")
    public String alterar(@RequestBody ConquistaObtida obj) {
        bd.save(obj);
        return "A conquista obtida foi alterada corretamente!";
    }

    @GetMapping("/api/conquista-obtida/{codigo}")
    public ConquistaObtida carregar(@PathVariable Long codigo) {
        Optional<ConquistaObtida> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/conquista-obtida/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/conquistas-obtidas")
    public List<ConquistaObtida> listar() {
        return bd.findAll();
    }
}

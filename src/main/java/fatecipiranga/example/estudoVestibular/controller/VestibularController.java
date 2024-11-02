package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Vestibular;
import fatecipiranga.example.estudoVestibular.repository.VestibularRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class VestibularController {

    @Autowired
    VestibularRepository bd;

    @PostMapping("/api/vestibular")
    public String gravar(@RequestBody Vestibular obj) {
        bd.save(obj);
        return "O vestibular " + obj.getNome() + " foi salvo corretamente!";
    }

    @PutMapping("/api/vestibular")
    public String alterar(@RequestBody Vestibular obj) {
        bd.save(obj);
        return "O vestibular " + obj.getNome() + " foi alterado corretamente!";
    }

    @GetMapping("/api/vestibular/{codigo}")
    public Vestibular carregar(@PathVariable Long codigo) {
        Optional<Vestibular> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/vestibular/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/vestibulares")
    public List<Vestibular> listar() {
        return bd.findAll();
    }
}

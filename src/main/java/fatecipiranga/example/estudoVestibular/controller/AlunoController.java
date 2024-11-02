package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import fatecipiranga.example.estudoVestibular.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AlunoController {

    @Autowired
    AlunoRepository bd;

    @PostMapping("/api/aluno")
    public String gravar(@RequestBody Aluno obj) {
        bd.save(obj);
        return "O aluno " + obj.getNome() + " foi salvo corretamente!";
    }

    @PutMapping("/api/aluno")
    public String alterar(@RequestBody Aluno obj) {
        bd.save(obj);
        return "O aluno " + obj.getNome() + " foi alterado corretamente!";
    }

    @GetMapping("/api/aluno/{codigo}")
    public Aluno carregar(@PathVariable Long codigo) {
        Optional<Aluno> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/aluno/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/alunos")
    public List<Aluno> listar() {
        return bd.findAll();
    }
}


package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Vestibular;
import fatecipiranga.example.estudoVestibular.repository.VestibularRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class VestibularController {

    @Autowired
    VestibularRepository vestRepo;

    @PostMapping("/api/vestibular")
    public ResponseEntity<Void> cadastrar(@RequestBody Vestibular vestibular) {
        if(vestRepo.procurarVestibular(vestibular.getNome(), vestibular.getAno(), vestibular.getSemestre()).isEmpty()){
            vestRepo.save(vestibular); // Salva o objeto "vestibular" no Banco de Dados
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
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

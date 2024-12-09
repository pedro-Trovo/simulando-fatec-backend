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
    public ResponseEntity<Void> alterar(@RequestBody Vestibular vestibular) {
        vestRepo.save(vestibular);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }

    @GetMapping("/api/vestibular/{codigo}")
    public ResponseEntity<Vestibular> carregar(@PathVariable Long codigo) {
        return vestRepo.findById(codigo)
                .map(ResponseEntity::ok) // Retorna 200 + o Vestibular encontrado
                .orElse(ResponseEntity.notFound().build()); // Retorna 404
    }

    @GetMapping("/api/vestibulares")
    public ResponseEntity<List<Vestibular>> listarTodosVestibulares() {
        // Busca todos os vestibulares no banco de dados
        List<Vestibular> vestibulares = vestRepo.findAll();

        if(vestibulares.isEmpty()){
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        return ResponseEntity.ok(vestibulares); // Retorna 200 + a lista de vestibulares
    }

    @DeleteMapping("/api/vestibular/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable Long codigo) {
        if(vestRepo.existsById(codigo)){
            vestRepo.deleteById(codigo);
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        else{
            return ResponseEntity.notFound().build(); // Retorna 404
        }
    }
}

package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Vestibular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.repository.ConquistaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ConquistaController {

    @Autowired
    ConquistaRepository conquistaRepo;

    @PostMapping("/api/conquista")
    public ResponseEntity<Void> cadastrar(@RequestBody Conquista conquista) {
        if(conquistaRepo.procurarConquista(conquista.getVestibular().getId(), conquista.getNome(), conquista.getDescricao()).isEmpty()){
            conquistaRepo.save(conquista); // Salva o objeto "conquista" no Banco de Dados
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
    }

    @PostMapping("api/conquistas")
    public ResponseEntity<String> cadastrarConquistas(@RequestBody List<Conquista> conquistas) {
        try{
            for(Conquista conquista : conquistas){
                if(conquistaRepo.procurarConquista(conquista.getVestibular().getId(), conquista.getNome(), conquista.getDescricao()).isEmpty()){
                    conquistaRepo.save(conquista); // Salva o objeto "conquista" no Banco de Dados
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna 400
        }
    }

    @PutMapping("/api/conquista")
    public ResponseEntity<Void> alterar(@RequestBody Conquista conquista) {
        conquistaRepo.save(conquista); // Salva o objeto "conquista" no Banco de Dados
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }

    @GetMapping("/api/conquista/{conquistaId}")
    public ResponseEntity<Conquista> carregar(@PathVariable Long conquistaId) {
        return conquistaRepo.findById(conquistaId)
                .map(ResponseEntity::ok) // Retorna 200 + a Conquista encontrada
                .orElse(ResponseEntity.notFound().build()); // Retorna 404
    }

    @GetMapping("/api/conquistas")
    public ResponseEntity<List<Conquista>> listarTodasConquistas() {
        // Busca todos os vestibulares no banco de dados
        List<Conquista> conquistas = conquistaRepo.findAll();

        if(conquistas.isEmpty()){
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        return ResponseEntity.ok(conquistas); // Retorna 200 + a lista de conquistas
    }

    @GetMapping("/api/conquistas/{vestibularId}")
    public ResponseEntity<List<Conquista>> listarTodasConquistasPorVestibular(@PathVariable Long vestibularId){
        Optional<List<Conquista>> conquistasPorVestibular = conquistaRepo.procurarConquistasPorVestibular(vestibularId);

        return conquistasPorVestibular
                .map(ResponseEntity::ok) // Retorna 200 + a lista de conquistas por vestibular
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
    }

    @DeleteMapping("/api/conquista/{conquistaId}")
    public ResponseEntity<Void> remover(@PathVariable Long conquistaId) {
        if(conquistaRepo.existsById(conquistaId)){
            conquistaRepo.deleteById(conquistaId);
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        else{
            return ResponseEntity.notFound().build(); // Retorna 404
        }
    }
}
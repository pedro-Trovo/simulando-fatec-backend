package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.ConquistaObtida;
import fatecipiranga.example.estudoVestibular.repository.ConquistaObtidaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ConquistaObtidaController {

    @Autowired
    ConquistaObtidaRepository conquistasObtRepo;

    @PostMapping("/api/conquista_obtida")
    public ResponseEntity<Void> gravar(@RequestBody ConquistaObtida conquistaObtida) {
        if(conquistasObtRepo.procurarConquistaObtida(conquistaObtida.getAluno().getId(), conquistaObtida.getConquista().getId()).isEmpty()){
            conquistasObtRepo.save(conquistaObtida); // Salva o objeto "conquistaObtida" no Banco de Dados
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
    }

    /*
    @PutMapping("/api/conquista_obtida")
    public ResponseEntity<Void> alterar(@RequestBody ConquistaObtida conquistaObtida) {
        conquistasObtRepo.save(conquistaObtida); // Salva o objeto "conquistaObtida" no Banco de Dados
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    */

    @GetMapping("/api/conquista_obtida/{conquistaObtidaId}")
    public ResponseEntity<ConquistaObtida> carregar(@PathVariable Long conquistaObtidaId) {
        return conquistasObtRepo.findById(conquistaObtidaId)
                .map(ResponseEntity::ok) // Retorna 200 + a Conquista encontrada
                .orElse(ResponseEntity.notFound().build()); // Retorna 404
    }

    @GetMapping("/api/conquistas_obtidas/{alunoId}")
    public ResponseEntity<List<ConquistaObtida>> listarTodasConquistasPorAluno(@PathVariable Long alunoId){
        Optional<List<ConquistaObtida>> conquistasObtidasPorAluno = conquistasObtRepo.procurarConquistaObtidaPorAluno(alunoId);

        return conquistasObtidasPorAluno
                .map(ResponseEntity::ok) // Retorna 200 + a lista de conquistas obtidas por aluno
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
    }

    @GetMapping("/api/conquistas_obtidas/{alunoId}/{vestibularId}")
    public ResponseEntity<List<ConquistaObtida>> listarTodasConquistasPorAlunoPorVestibular(
            @PathVariable Long alunoId,
            @PathVariable Long vestibularId
    ) {
        Optional<List<ConquistaObtida>> conquistasObtidasPorAlunoPorVestibular = conquistasObtRepo.procurarConquistaObtidaPorAlunoPorVestibular(alunoId, vestibularId);

        return conquistasObtidasPorAlunoPorVestibular
                .map(ResponseEntity::ok) // Retorna 200 + a lista de conquistas obtidas por aluno
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
    }

    @DeleteMapping("/api/conquista_obtida/{conquistaObtidaId}")
    public ResponseEntity<Void> remover(@PathVariable Long conquistaObtidaId) {
        if(conquistasObtRepo.existsById(conquistaObtidaId)){
            conquistasObtRepo.deleteById(conquistaObtidaId);
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        else{
            return ResponseEntity.notFound().build(); // Retorna 404
        }
    }
}
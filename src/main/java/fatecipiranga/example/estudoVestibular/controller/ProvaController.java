package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Prova;
import fatecipiranga.example.estudoVestibular.model.ProvaId;
import fatecipiranga.example.estudoVestibular.model.Vestibular;
import fatecipiranga.example.estudoVestibular.repository.ProvaRepository;
import fatecipiranga.example.estudoVestibular.repository.VestibularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProvaController {

  @Autowired
  ProvaRepository provaRepo;

  @Autowired
  VestibularRepository vestRepo;

  @PostMapping("/api/prova")
  public ResponseEntity<Void> cadastrar(@RequestBody ProvaId provaId) {
    if (provaRepo.procurarProva(provaId.getVestibular().getId(), provaId.getAno(), provaId.getSemestre()).isEmpty()) {
      Prova prova = new Prova(provaId);
      provaRepo.save(prova); // Salva o objeto "vestibular" no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PostMapping("/api/provas")
  public ResponseEntity<String> cadastrarVestibulares(@RequestBody List<ProvaId> provasIds) {
    try {
      for (ProvaId provaId : provasIds) {
        if (provaRepo.procurarProva(provaId.getVestibular().getId(), provaId.getAno(), provaId.getSemestre()).isEmpty()) {
          Prova prova = new Prova(provaId);
          provaRepo.save(prova); // Salva o objeto "vestibular" no Banco de Dados
        }
      }
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna 400
    }
  }

  @GetMapping("/api/prova/{vestibularId}/{ano}/{semestre}")
  public ResponseEntity<Prova> carregar(
          @PathVariable Long vestibularId,
          @PathVariable int ano,
          @PathVariable int semestre
  ) {
    Vestibular vestibular = vestRepo.findById(vestibularId).orElse(null);
    if (vestibular == null) {
      return ResponseEntity.notFound().build(); // Retorna 404
    }

    ProvaId provaId = new ProvaId(vestibular, ano, semestre);

    return provaRepo.findById(provaId)
            .map(ResponseEntity::ok) // Retorna 200 + o Vestibular encontrado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/provas/{vestibularId}")
  public ResponseEntity<List<Prova>> listarTodasProvasPorVestibular(@PathVariable Long vestibularId) {
    // Busca todas as Provas por Vestibular no banco de dados
    Optional<List<Prova>> provasPorVestibular = provaRepo.procurarProvaPorVestibular(vestibularId);

    return provasPorVestibular
            .map(ResponseEntity::ok) // Retorna 204
            .orElse(ResponseEntity.noContent().build()); // Retorna 200 + a lista de conquistas por vestibular
  }

  @GetMapping("/api/provas")
  public ResponseEntity<List<Prova>> listarTodasProvasPorVestibular() {
    // Busca todas as Provas no banco de dados
    List<Prova> provas = provaRepo.findAll();

    if(provas.isEmpty()){
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    return ResponseEntity.ok(provas); // Retorna 200 + a lista de conquistas
  }

  @DeleteMapping("/api/prova/{vestibularId}/{ano}/{semestre}")
  public ResponseEntity<Void> remover(
          @PathVariable Long vestibularId,
          @PathVariable int ano,
          @PathVariable int semestre
  ) {
    Vestibular vestibular = vestRepo.findById(vestibularId).orElse(null);
    if (vestibular == null) {
      return ResponseEntity.notFound().build(); // Retorna 404
    }

    ProvaId provaId = new ProvaId(vestibular, ano, semestre);

    if(provaRepo.existsById(provaId)){
      provaRepo.deleteById(provaId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
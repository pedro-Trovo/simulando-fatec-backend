package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Prova;
import fatecipiranga.example.estudoVestibular.model.ProvaId;
import fatecipiranga.example.estudoVestibular.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProvaController {

  @Autowired
  ProvaRepository provaRepo;

  @PostMapping("/api/prova")
  public ResponseEntity<Void> cadastrar(@RequestBody ProvaId provaId) {
    // Verifica se o item já existe no Banco de Dados
    if (provaRepo.procurarProva(
            provaId.getAno(),
            provaId.getSemestre()
    ).isEmpty()) {
      // Instancia objeto do tipo Prova (contendo um ProvaId)
      Prova prova = new Prova(provaId);
      provaRepo.save(prova); // Salva o objeto no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PostMapping("/api/provas")
  public ResponseEntity<String> cadastrarProvas(@RequestBody List<ProvaId> provasIds) {
    try {
      // Loop For para iterar sobre o array
      for (ProvaId provaId : provasIds) {
        // Verifica se o item já existe no Banco de Dados
        if (provaRepo.procurarProva(
                provaId.getAno(),
                provaId.getSemestre()
        ).isEmpty()) {
          // Instancia objeto do tipo Prova (contendo um ProvaId)
          Prova prova = new Prova(provaId);
          provaRepo.save(prova); // Salva o objeto no Banco de Dados
        }
      }
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna 400
    }
  }

  @GetMapping("/api/prova/{ano}/{semestre}")
  public ResponseEntity<Prova> carregar(
          @PathVariable int ano,
          @PathVariable int semestre
  ) {
    // Instancia objeto do tipo ProvaId (contendo um Vestibular, ano, semestre)
    ProvaId provaId = new ProvaId( ano, semestre);

    // Procura item por ID
    return provaRepo.findById(provaId)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/provas")
  public ResponseEntity<List<Prova>> listarTodasProvasPorVestibular() {
    // Busca todas as Provas no banco de dados
    List<Prova> provas = provaRepo.findAll();

    if(provas.isEmpty()){
      return ResponseEntity.notFound().build(); // Retorna 404
    }
    return ResponseEntity.ok(provas); // Retorna 200 + a lista de itens pesquisados
  }

  @DeleteMapping("/api/prova/{ano}/{semestre}")
  public ResponseEntity<Void> remover(
          @PathVariable int ano,
          @PathVariable int semestre
  ) {
    // Instancia objeto do tipo ProvaId (contendo um Vestibular, ano, semestre)
    ProvaId provaId = new ProvaId(ano, semestre);

    // Checa se o item existe por ID
    if(provaRepo.existsById(provaId)){
      // Deleta o item por ID
      provaRepo.deleteById(provaId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
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
    // Verifica se o item já existe no Banco de Dados
    if(conquistaRepo.procurarConquista(
            conquista.getVestibular().getId(),
            conquista.getNome(),
            conquista.getDescricao()
    ).isEmpty()){
      conquistaRepo.save(conquista); // Salva o objeto no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PostMapping("api/conquistas")
  public ResponseEntity<String> cadastrarConquistas(@RequestBody List<Conquista> conquistas) {
    try{
      // Loop For para iterar sobre o array
      for(Conquista conquista : conquistas){
        // Verifica se o item já existe no Banco de Dados
        if(conquistaRepo.procurarConquista(
                conquista.getVestibular().getId(),
                conquista.getNome(),
                conquista.getDescricao()
        ).isEmpty()){
          conquistaRepo.save(conquista); // Salva o objeto no Banco de Dados
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
    conquistaRepo.save(conquista); // Salva o objeto no Banco de Dados
    return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
  }

  @GetMapping("/api/conquista/{conquistaId}")
  public ResponseEntity<Conquista> carregar(@PathVariable Long conquistaId) {
    // Procura item por ID
    return conquistaRepo.findById(conquistaId)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/conquistas")
  public ResponseEntity<List<Conquista>> listarTodasConquistas() {
    // Busca todos os itens no banco de dados
    List<Conquista> conquistas = conquistaRepo.findAll();

    // Checa se a array está vazio
    if(conquistas.isEmpty()){
      return ResponseEntity.notFound().build(); // Retorna 404
    }
    return ResponseEntity.ok(conquistas); // Retorna 200 + a lista de itens pesquisados
  }

  @GetMapping("/api/conquistas/{vestibularId}")
  public ResponseEntity<List<Conquista>> listarTodasConquistasPorVestibular(@PathVariable Long vestibularId){
    Optional<List<Conquista>> conquistasPorVestibular = conquistaRepo.procurarConquistasPorVestibular(vestibularId);

    return conquistasPorVestibular
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @DeleteMapping("/api/conquista/{conquistaId}")
  public ResponseEntity<Void> remover(@PathVariable Long conquistaId) {
    // Checa se o item existe por ID
    if(conquistaRepo.existsById(conquistaId)){
      // Deleta o item por ID
      conquistaRepo.deleteById(conquistaId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
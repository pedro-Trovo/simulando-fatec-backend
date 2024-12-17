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
    // Verifica se o item já existe no Banco de Dados
    if(vestRepo.procurarVestibular(vestibular.getNome()).isEmpty()){
      vestRepo.save(vestibular); // Salva o objeto no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PostMapping("/api/vestibulares")
  public ResponseEntity<String> cadastrarVestibulares(@RequestBody List<Vestibular> vestibulares) {
    try{
      // Loop For para iterar sobre o array
      for(Vestibular vestibular : vestibulares){
        // Verifica se o item já existe no Banco de Dados
        if(vestRepo.procurarVestibular(vestibular.getNome()).isEmpty()){
          vestRepo.save(vestibular); // Salva o objeto no Banco de Dados
        }
      }
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    catch(Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Retorna 400
    }
  }

  @PutMapping("/api/vestibular")
  public ResponseEntity<Void> alterar(@RequestBody Vestibular vestibular) {
    vestRepo.save(vestibular); // Salva o objeto no Banco de Dados
    return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
  }

  @GetMapping("/api/vestibular/{codigo}")
  public ResponseEntity<Vestibular> carregar(@PathVariable Long codigo) {
    // Procura item por ID
    return vestRepo.findById(codigo)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/vestibulares")
  public ResponseEntity<List<Vestibular>> listarTodosVestibulares() {
    // Busca todos os itens no banco de dados
    List<Vestibular> vestibulares = vestRepo.findAll();

    // Checa se a array está vazio
    if(vestibulares.isEmpty()){
      return ResponseEntity.notFound().build(); // Retorna 404
    }
    return ResponseEntity.ok(vestibulares); // Retorna 200 + a lista de itens pesquisados
  }

  @DeleteMapping("/api/vestibular/{codigo}")
  public ResponseEntity<Void> remover(@PathVariable Long codigo) {
    // Checa se o item existe por ID
    if(vestRepo.existsById(codigo)){
      // Deleta o item por ID
      vestRepo.deleteById(codigo);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
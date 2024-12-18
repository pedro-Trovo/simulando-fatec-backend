package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.*;
import fatecipiranga.example.estudoVestibular.repository.VestibularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.repository.QuestaoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestaoController {

  @Autowired
  QuestaoRepository questaoRepo;
  VestibularRepository vestRepo;

  @PostMapping("/api/questao")
  public ResponseEntity<Void> cadastrar(@RequestBody Questao questao) {
    // Verifica se o item já existe no Banco de Dados
    if(questaoRepo.procurarQuestao(
            questao.getEnunciado(),
            questao.getPergunta()
    ).isEmpty()){
      // Armazena a questão na alternativa (cria uma ligação entre eles)
      for(Alternativa alternativa : questao.getAlternativas()) {
        alternativa.setQuestao(questao);
      }

      questaoRepo.save(questao); // Salva o objeto no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PutMapping("/api/questao")
  public ResponseEntity<Void> alterar(@RequestBody Questao questao) {
    // Armazena a questão na alternativa (cria uma ligação entre eles)
    for(Alternativa alternativa : questao.getAlternativas()) {
      alternativa.setQuestao(questao);
    }

    questaoRepo.save(questao); // Salva o objeto no Banco de Dados
    return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
  }

  @GetMapping("/api/questao/{questaoId}")
  public ResponseEntity<Questao> carregar(@PathVariable Long questaoId) {
    // Procura item por ID
    return questaoRepo.findById(questaoId)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/questoes/prova/{vestibularId}/{ano}/{semestre}")
  public ResponseEntity<List<Questao>> listarTodasQuestoesPorProva(
          @PathVariable Long vestibularId,
          @PathVariable int ano,
          @PathVariable int semestre
  ){
    Optional<List<Questao>> questoesPorProva = questaoRepo.procurarQuestoesPorProva(vestibularId, ano, semestre);

    return questoesPorProva
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/questoes/vestibular/{vestibularId}")
  public ResponseEntity<List<Questao>> listarTodasQuestoesPorVestibular(@PathVariable Long vestibularId){
    Optional<List<Questao>> questoesPorVestibular = questaoRepo.procurarQuestoesPorVestibular(vestibularId);

    return questoesPorVestibular
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @DeleteMapping("/api/questao/{questaoId}")
  public ResponseEntity<Void> remover(@PathVariable Long questaoId) {
    // Checa se o item existe por ID
    if(questaoRepo.existsById(questaoId)){
      // Deleta o item por ID
      questaoRepo.deleteById(questaoId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
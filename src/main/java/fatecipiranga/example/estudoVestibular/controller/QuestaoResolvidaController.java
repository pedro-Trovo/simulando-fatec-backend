package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import fatecipiranga.example.estudoVestibular.repository.QuestaoResolvidaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestaoResolvidaController {

  @Autowired
  QuestaoResolvidaRepository questaoResolRepo;

  @PostMapping("/api/questao-resolvida")
  public ResponseEntity<Void> cadastrar(@RequestBody QuestaoResolvida questaoResolvida) {
    // Verifica se o item já existe no Banco de Dados
    if(questaoResolRepo.procurarQuestaoResolvida(
            questaoResolvida.getProvaEfetuada().getId(),
            questaoResolvida.getQuestao().getId()).isEmpty()
    ){
      // Verifica se a Questão informada faz parte da Prova referenciada por ProvaEfetuada
      if(questaoResolvida.getProvaEfetuada().getProva().getId()
              .equals(questaoResolvida.getQuestao().getProva().getId())
      ){
        questaoResolRepo.save(questaoResolvida); // Salva o objeto no Banco de Dados
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
      }
      else{
        return ResponseEntity.badRequest().build(); // Retorna 400
      }
    }
    else {
      // Verifica se a Questão informada faz parte da Prova referenciada por ProvaEfetuada
      if(questaoResolvida.getProvaEfetuada().getProva().getId()
              .equals(questaoResolvida.getQuestao().getProva().getId())
      ){
        // Caso não haja "data" informada, definir ela como a data deste exato momento
        if (questaoResolvida.getData() == null) {
          questaoResolvida.setData(LocalDate.now());
        }

        questaoResolRepo.alterarQuestaoResolvida(
                questaoResolvida.getProvaEfetuada().getId(),
                questaoResolvida.getQuestao().getId(),
                questaoResolvida.isAcertou(),
                questaoResolvida.getLetraEscolhida(),
                questaoResolvida.getData()
        );
        return ResponseEntity.ok().build(); // Retorna 200
      }
      else{
        return ResponseEntity.badRequest().build(); // Retorna 400
      }
    }
  }

  /*
  @PutMapping("/api/questao-resolvida")
  public ResponseEntity<Void> alterar(@RequestBody QuestaoResolvida questaoResolvida) {
      questaoResolRepo.save(questaoResolvida); // Salva o objeto "questaoResolvida" no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
  }
   */

  @GetMapping("/api/questao-resolvida/{questaoResolvidaId}")
  public ResponseEntity<QuestaoResolvida> carregar(@PathVariable Long questaoResolvidaId) {
    // Procura item por ID
    return questaoResolRepo.findById(questaoResolvidaId)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/questoes-resolvidas/aluno/{alunoId}")
  public ResponseEntity<List<QuestaoResolvida>> listarTodasQuestoesResolvidasPorAluno(@PathVariable Long alunoId){
    Optional<List<QuestaoResolvida>> questoesResolvidasPorAluno = questaoResolRepo.procurarQuestoesResolvidasPorAluno(alunoId);

    return questoesResolvidasPorAluno
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/questoes-resolvidas/aluno/{alunoId}/prova/{vestibularId}/{ano}/{semestre}")
  public ResponseEntity<List<QuestaoResolvida>> listarTodasQuestoesResolvidasPorAlunoPorProva(
          @PathVariable Long alunoId,
          @PathVariable Long vestibularId,
          @PathVariable int ano,
          @PathVariable int semestre
  ){
    Optional<List<QuestaoResolvida>> questoesResolvidasPorAlunoPorProva = questaoResolRepo.procurarQuestoesResolvidasPorAlunoPorProva(alunoId, vestibularId, ano, semestre);

    return questoesResolvidasPorAlunoPorProva
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/questoes-resolvidas/aluno/{alunoId}/vestibular/{vestibularId}")
  public ResponseEntity<List<QuestaoResolvida>> listarTodasQuestoesResolvidasPorALunoPorVestibular(
          @PathVariable Long alunoId,
          @PathVariable Long vestibularId
  ){
    Optional<List<QuestaoResolvida>> questoesResolvidasPorAlunoPorVestibular = questaoResolRepo.procurarQuestoesResolvidasPorAlunoPorVestibular(alunoId, vestibularId);

    return questoesResolvidasPorAlunoPorVestibular
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @DeleteMapping("/api/questao-resolvida/{questaoResolvidaId}")
  public ResponseEntity<Void> remover(@PathVariable Long questaoResolvidaId) {
    // Checa se o item existe por ID
    if(questaoResolRepo.existsById(questaoResolvidaId)){
      // Deleta o item por ID
      questaoResolRepo.deleteById(questaoResolvidaId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
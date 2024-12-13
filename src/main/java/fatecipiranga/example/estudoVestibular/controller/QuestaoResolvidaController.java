package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Alternativa;
import fatecipiranga.example.estudoVestibular.model.Questao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import fatecipiranga.example.estudoVestibular.repository.QuestaoResolvidaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestaoResolvidaController {

    @Autowired
    QuestaoResolvidaRepository questaoResolRepo;

    @PostMapping("/api/questao-resolvida")
    public ResponseEntity<Void> cadastrar(@RequestBody QuestaoResolvida questaoResolvida) {
        // Verifica se a questão resolvida recebida já existe no banco de dados
        if(questaoResolRepo.procurarQuestaoResolvida(questaoResolvida.getAluno().getId(), questaoResolvida.getQuestao().getId()).isEmpty()){
            questaoResolRepo.save(questaoResolvida); // Salva o objeto "questaoResolvida" no Banco de Dados
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        else {
            // Altera a questão resolvida salva no banco de dados
            questaoResolRepo.alterarQuestaoResolvida(questaoResolvida.getAluno().getId(), questaoResolvida.getQuestao().getId(), questaoResolvida.isAcertou());
            return ResponseEntity.ok().build(); // Retorna 200
        }
    }

    @PutMapping("/api/questao-resolvida")
    public ResponseEntity<Void> alterar(@RequestBody QuestaoResolvida questaoResolvida) {
        questaoResolRepo.save(questaoResolvida); // Salva o objeto "questaoResolvida" no Banco de Dados
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }

    @GetMapping("/api/questao-resolvida/{questaoResolvidaId}")
    public ResponseEntity<QuestaoResolvida> carregar(@PathVariable Long questaoResolvidaId) {
        return questaoResolRepo.findById(questaoResolvidaId)
                .map(ResponseEntity::ok) // Retorna 200 + a Questão resolvida encontrada
                .orElse(ResponseEntity.notFound().build()); // Retorna 404
    }

    @GetMapping("/api/questoes-resolvidas/aluno/{alunoId}")
    public ResponseEntity<List<QuestaoResolvida>> listarTodasQuestoesResolvidasPorAluno(Long alunoId){
        Optional<List<QuestaoResolvida>> questoesResolvidasPorAluno = questaoResolRepo.procurarQuestoesResolvidasPorAluno(alunoId);

        return questoesResolvidasPorAluno
                .map(ResponseEntity::ok) // Retorna 200 + as Questões resolvidas por aluno
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
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
                .map(ResponseEntity::ok) // Retorna 200 + as Questoes resolvidas por aluno por prova
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
    }

    @GetMapping("/api/questoes-resolvidas/aluno/{alunoId}/vestibular/{vestibularId}")
    public ResponseEntity<List<QuestaoResolvida>> listarTodasQuestoesResolvidasPorALunoPorVestibular(
            @PathVariable Long alunoId,
            @PathVariable Long vestibularId
    ){
        Optional<List<QuestaoResolvida>> questoesResolvidasPorAlunoPorVestibular = questaoResolRepo.procurarQuestoesResolvidasPorAlunoPorVestibular(alunoId, vestibularId);

        return questoesResolvidasPorAlunoPorVestibular
                .map(ResponseEntity::ok) // Retorna 200 + as Questões resolvidas por aluno por vestibular
                .orElse(ResponseEntity.noContent().build()); // Retorna 204
    }

    @DeleteMapping("/api/questao-resolvida/{questaoResolvidaId}")
    public ResponseEntity<Void> remover(@PathVariable Long questaoResolvidaId) {
        if(questaoResolRepo.existsById(questaoResolvidaId)){
            questaoResolRepo.deleteById(questaoResolvidaId);
            return ResponseEntity.noContent().build(); // Retorna 204
        }
        else{
            return ResponseEntity.notFound().build(); // Retorna 404
        }
    }
}


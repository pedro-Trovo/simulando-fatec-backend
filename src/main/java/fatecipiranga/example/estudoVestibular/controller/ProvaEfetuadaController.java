package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.model.ProvaEfetuada;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import fatecipiranga.example.estudoVestibular.repository.ProvaEfetuadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ProvaEfetuadaController {

  @Autowired
  ProvaEfetuadaRepository provaEfetRepo;

  @PostMapping("/api/prova-efetuada")
  public ResponseEntity<Void> cadastrar(@RequestBody ProvaEfetuada provaEfetuada) {

    // Procura o item no banco de dados
    Optional<ProvaEfetuada> provaEfetuadaBancoDados = provaEfetRepo.procurarProvaEfetuada(
            provaEfetuada.getAluno().getId(),
            provaEfetuada.getProva().getId().getVestibular().getId(),
            provaEfetuada.getProva().getId().getAno(),
            provaEfetuada.getProva().getId().getSemestre()
    );

    // Verifica se o item já existe no Banco de Dados
    if(provaEfetuadaBancoDados.isEmpty()) {
      provaEfetRepo.save(provaEfetuada); // Salva o objeto no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    else {
      // Caso não haja "data" informada, definir ela como a data deste exato momento
      if (provaEfetuada.getData() == null) {
        provaEfetuada.setData(LocalDate.now());
      }

      // Caso não haja "situação", definir como "Em Andamento"
      if (provaEfetuada.getSituacao() == null){
        provaEfetuada.setSituacao("Em Andamento");
      }

      provaEfetRepo.alterarProvaEfetuada(
              provaEfetuadaBancoDados.get().getId(),
              provaEfetuada.getData(),
              provaEfetuada.getSituacao(),
              provaEfetuada.getTempoAcumuladoSegundos()
      );
      return ResponseEntity.ok().build(); // Retorna 200
    }
  }

  @GetMapping("/api/prova-efetuada/{provaEfetuadaId}")
  public ResponseEntity<ProvaEfetuada> carregar(@PathVariable Long provaEfetuadaId) {
    // Procura item por ID
    return provaEfetRepo.findById(provaEfetuadaId)
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/prova-efetuada/aluno/{alunoId}")
  public ResponseEntity<List<ProvaEfetuada>> listarTodasProvasEfetuadasPorAluno(@PathVariable Long alunoId){
    Optional<List<ProvaEfetuada>> provasEfetuadasPorAluno = provaEfetRepo.procurarProvasEfetuadasPorAluno(alunoId);

    return provasEfetuadasPorAluno
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/prova-efetuada/aluno/{alunoId}/prova/{vestibularId}/{ano}/{semestre}")
  public ResponseEntity<ProvaEfetuada> listarTodasProvasEfetuadasPorAlunoPorProva(
          @PathVariable Long alunoId,
          @PathVariable Long vestibularId,
          @PathVariable int ano,
          @PathVariable int semestre
  ){
    Optional<ProvaEfetuada> provaEfetuadaPorAlunoPorProva = provaEfetRepo.procurarProvaEfetuada(alunoId, vestibularId, ano, semestre);

    return provaEfetuadaPorAlunoPorProva
            .map(ResponseEntity::ok) // Retorna 200 + o item pesquisado
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/prova-efetuada/aluno/{alunoId}/vestibular/{vestibularId}")
  public ResponseEntity<List<ProvaEfetuada>> listarTodasProvasEfetuadasPorALunoPorVestibular(
          @PathVariable Long alunoId,
          @PathVariable Long vestibularId
  ){
    Optional<List<ProvaEfetuada>> provasEfetuadasPorAlunoPorVestibular = provaEfetRepo.procurarProvasEfetuadasPorAlunoPorVestibular(alunoId, vestibularId);

    return provasEfetuadasPorAlunoPorVestibular
            .map(ResponseEntity::ok) // Retorna 200 + a lista de itens pesquisados
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @DeleteMapping("/api/prova-efetuada/{provaEfetuadaId}")
  public ResponseEntity<Void> remover(@PathVariable Long provaEfetuadaId) {
    // Checa se o item existe por ID
    if(provaEfetRepo.existsById(provaEfetuadaId)){
      // Deleta o item por ID
      provaEfetRepo.deleteById(provaEfetuadaId);
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    else{
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}

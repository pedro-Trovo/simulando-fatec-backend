package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.model.Conquista;
import fatecipiranga.example.estudoVestibular.model.ProvaEfetuada;
import fatecipiranga.example.estudoVestibular.model.QuestaoResolvida;
import fatecipiranga.example.estudoVestibular.repository.ProvaEfetuadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    // Verifica se a prova efetuada recebida já existe no banco de dados
    if(provaEfetuadaBancoDados.isEmpty()) {
      provaEfetRepo.save(provaEfetuada); // Salva o objeto "provaEfetuada" no Banco de Dados
      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    else {
      if (provaEfetuada.getData() == null) {
        provaEfetuada.setData(LocalDate.now());
      }

      if (provaEfetuada.getSituacao() == null){
        provaEfetuada.setSituacao("Em Andamento");
      }

      // Altera a questão resolvida salva no banco de dados
      provaEfetRepo.alterarProvaEfetuada(
              provaEfetuadaBancoDados.get().getId(),
              provaEfetuada.getData(),
              provaEfetuada.getSituacao(),
              provaEfetuada.getTempoAcumuladoSegundos()
      );
      return ResponseEntity.ok().build(); // Retorna 200
    }
  }


}

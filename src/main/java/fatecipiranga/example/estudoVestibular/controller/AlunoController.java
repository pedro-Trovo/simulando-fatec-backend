package fatecipiranga.example.estudoVestibular.controller;

import fatecipiranga.example.estudoVestibular.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import fatecipiranga.example.estudoVestibular.repository.AlunoRepository;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

@RestController
public class AlunoController {

  @Autowired
  AlunoRepository alunoRepo;

  @PostMapping("/api/aluno")
  public ResponseEntity<Void> cadastrar(@RequestBody Aluno aluno) {
    if(alunoRepo.procurarLogin(aluno.getEmail()).isEmpty()){
      // Cria o Hash da senha usando o BCrypt
      String senha_hash = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt(14));
      aluno.setSenha(senha_hash); // Armazena o Hash no lugar da senha padrão
      alunoRepo.save(aluno); // Salva o objeto "aluno" no Banco de Dados

      return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
  }

  @PutMapping("/api/aluno")
  public ResponseEntity<Void> alterar(@RequestBody Aluno aluno) {
    // Cria o Hash da senha usando o BCrypt
    String senha_hash = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt(14));
    aluno.setSenha(senha_hash); // Armazena o Hash no lugar da senha padrão
    alunoRepo.save(aluno); // Salva o objeto "aluno" no Banco de Dados

    return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
  }

  @PostMapping("/api/aluno/login")
  public ResponseEntity<Long> efetuarLogin(@RequestBody LoginDTO loginDTO) {
    return alunoRepo.procurarLogin(loginDTO.getEmail())
            // Checa se a senha digitada é igual a que está no banco de dados
            .filter(aluno -> BCrypt.checkpw(loginDTO.getSenha(), aluno.getSenha()))
            .map(Aluno::getId) // Pega o ID do aluno
            .map(ResponseEntity::ok) // Retorna o ID do Aluno
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/aluno/{alunoId}")
  public ResponseEntity<Aluno> carregar(@PathVariable Long alunoId) {
    return alunoRepo.findById(alunoId)
            .map(ResponseEntity::ok) // Retorna 200 + a Conquista encontrada
            .orElse(ResponseEntity.notFound().build()); // Retorna 404
  }

  @GetMapping("/api/alunos")
  public ResponseEntity<List<Aluno>> listarTodosAlunos() {
    // Busca todos os alunos no banco de dados
    List<Aluno> alunos = alunoRepo.findAll();

    // Checa se a array "alunos" está vazio
    if (alunos.isEmpty()) {
      return ResponseEntity.noContent().build(); // Retorna 204
    }
    return ResponseEntity.ok(alunos); // Retorna 200 + a lista de alunos
  }

  @DeleteMapping("/api/aluno/{codigo}")
  public ResponseEntity<Void> remover(@PathVariable Long codigo) {
    if (alunoRepo.existsById(codigo)) {
      alunoRepo.deleteById(codigo);
      return ResponseEntity.noContent().build(); // Retorna 204
    } else {
      return ResponseEntity.notFound().build(); // Retorna 404
    }
  }
}
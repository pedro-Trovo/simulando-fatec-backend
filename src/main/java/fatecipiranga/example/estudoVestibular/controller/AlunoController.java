package fatecipiranga.example.estudoVestibular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fatecipiranga.example.estudoVestibular.model.Aluno;
import fatecipiranga.example.estudoVestibular.repository.AlunoRepository;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@RestController
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepo;

    @PostMapping("/api/aluno")
    public ResponseEntity cadastrar(@RequestBody Aluno aluno) {
        if(alunoRepo.checarEmailExiste(aluno.getEmail())){
            // Cria o Hash da senha usando o BCrypt
            String senha_hash = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt(14));
            aluno.setSenha(senha_hash); // Armazena o Hash no lugar da senha padrão
            alunoRepo.save(aluno); // Salva o objeto "aluno" no Banco de Dados

            return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400
    }

    @PutMapping("/api/aluno")
    public ResponseEntity alterar(@RequestBody Aluno aluno) {
        // Cria o Hash da senha usando o BCrypt
        String senha_hash = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt(14));
        aluno.setSenha(senha_hash); // Armazena o Hash no lugar da senha padrão
        alunoRepo.save(aluno); // Salva o objeto "aluno" no Banco de Dados

        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201
    }

    @GetMapping("/api/aluno/{codigo}")
    public Aluno carregar(@PathVariable Long codigo) {
        Optional<Aluno> obj = bd.findById(codigo);
        return obj.orElse(null);
    }

    @DeleteMapping("/api/aluno/{codigo}")
    public String remover(@PathVariable Long codigo) {
        bd.deleteById(codigo);
        return "Registro " + codigo + " removido com sucesso!";
    }

    @GetMapping("/api/alunos")
    public List<Aluno> listar() {
        return bd.findAll();
    }
}


package com.example.demo.Controller;

import com.example.demo.Entity.Lembretes;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.LembreteRepository;
import com.example.demo.Service.LembreteService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private LembreteRepository lembreteRepository;
    @Autowired
    private LembreteService lembreteService;

    @GetMapping()
    public ResponseEntity<List<Lembretes>> findAll() {
        try {
            return ResponseEntity.ok(lembreteService.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> idLembretes(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok(lembreteService.procurarLembrete(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> cadastrarLembrete(@RequestBody final Lembretes lembretes){
        try{
            this.lembreteService.cadastrarLembrete(lembretes);
            return ResponseEntity.ok("Condutor cadastrado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Lembretes lembretes) {
        Optional<Lembretes> lembreteExistente = lembreteRepository.findById(id);

        if (lembreteExistente.isPresent()) { //not null

            //atribui o valor presente dentro do Optional chamado condutorExistente para a variável condutorAtualizado.
            Lembretes lembreteAtualizado = lembreteExistente.get();

            //chama atualizarCondutor passando o ID do condutor atualizado e o objeto condutor que vai ser usado pra atualizar os dados
            lembreteService.atualizarLembrete(lembreteAtualizado.getId(), lembretes);

            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {

            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        Optional<Lembretes> optionalLembretes = lembreteRepository.findById(id);

        if (optionalLembretes.isPresent()) {
            Lembretes lembretes = optionalLembretes.get();

            lembreteRepository.delete(lembretes);
            return ResponseEntity.ok().body("O registro do condutor foi deletado com sucesso");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.demo.Controller;

import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.PessoaRepository;
import com.example.demo.Service.PessoaService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService PessoaService;
    @Autowired
    private PessoaRepository PessoaRepository;

    @GetMapping()
    public ResponseEntity<List<Pessoa>> findAll() {
        try {
            return ResponseEntity.ok(PessoaService.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> idPessoas(@RequestParam("id") final Long id){
        try{
            return ResponseEntity.ok(PessoaService.procurarPessoa(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody final Pessoa pessoa){
        try{
            this.PessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Condutor cadastrado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = PessoaRepository.findById(id);

        if (pessoaExistente.isPresent()) { //not null

            //atribui o valor presente dentro do Optional chamado condutorExistente para a variável condutorAtualizado.
            Pessoa condutorAtualizado = pessoaExistente.get();

            //chama atualizarCondutor passando o ID do condutor atualizado e o objeto condutor que vai ser usado pra atualizar os dados
            PessoaService.atualizarPessoa(condutorAtualizado.getId(), pessoa);

            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {

            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        Optional<Pessoa> optionalPessoa = PessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();

                PessoaRepository.delete(pessoa);
                return ResponseEntity.ok().body("O registro do condutor foi deletado com sucesso");
            }
         else {
            return ResponseEntity.notFound().build();
        }
    }






}

package com.example.demo.Controller;

import com.example.demo.Entity.Pessoa;
import com.example.demo.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService PessoaService;

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



}

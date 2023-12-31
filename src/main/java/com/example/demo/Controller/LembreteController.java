package com.example.demo.Controller;

import com.example.demo.Entity.Lembretes;
import com.example.demo.Repisotory.LembreteRepository;
import com.example.demo.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/lembrete")
public class LembreteController {


    @Autowired
    private LembreteRepository Repository;
    @Autowired
    private LembreteService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Lembretes>> lista(){
        List<Lembretes> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/pessoa/{nome}")
    public ResponseEntity<List<Lembretes>> parcelas(@PathVariable(value = "nome") String nomePessoa){
        List<Lembretes> listarnome= Service.achaNome(nomePessoa);
        return ResponseEntity.ok(listarnome);
    }


    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Lembretes listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> teste(@PathVariable Long id){
        Optional<Lembretes> optionalLembrete = Repository.findById(id);

        if (optionalLembrete.isPresent()) {
            Lembretes lembrete = optionalLembrete.get();
            return ResponseEntity.ok(lembrete);
        } else {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.");
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Lembretes cadastro){
        try{
            this.Service.cadastrar(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO:"+e.getMessage());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Lembretes> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Lembretes atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
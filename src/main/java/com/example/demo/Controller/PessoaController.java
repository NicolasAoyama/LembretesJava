package com.example.demo.Controller;

import com.example.demo.DTO.PessoaConverter;
import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.PessoaRepository;
import com.example.demo.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository Repository;
    @Autowired
    private PessoaService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<PessoaDTO>> lista(){
        List<Pessoa> listaPessoas = Service.listartudo();

        List<PessoaDTO> listaPessoaDTOs = new ArrayList<>();
        for (Pessoa pessoa : listaPessoas) {
            PessoaDTO pessoaDTO = PessoaConverter.convertPessoaToDTO(pessoa);
            listaPessoaDTOs.add(pessoaDTO);
        }

        return ResponseEntity.ok(listaPessoaDTOs);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Pessoa listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> teste(@PathVariable Long id){
        Pessoa listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa cadastro){
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
    @GetMapping("/lista/pessoa/{nome}")
    public ResponseEntity<List<Pessoa>> parcelas(@PathVariable(value = "nome") String nomePessoa){
        List<Pessoa> listarNome = Service.achaNome(nomePessoa);
        return ResponseEntity.ok(listarNome);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Pessoa> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/lista/pessoa/{nome}")
    public ResponseEntity<List<Pessoa>> nameSearch(@PathVariable(value = "nome") String nomePessoa){
        List<Pessoa> listarNome = Service.achaNome(nomePessoa);
        return ResponseEntity.ok(listarNome);
    }


    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Pessoa atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
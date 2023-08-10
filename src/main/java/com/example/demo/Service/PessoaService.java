package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository PessoaRepository;

    public List<Pessoa> findAll(){
        return PessoaRepository.findAll();
    }

    public Optional<Pessoa> procurarPessoa(Long id){
        if (!PessoaRepository.idExistente(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Pessoa> pessoa = this.PessoaRepository.findById(id);
            return pessoa;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrarPessoa(final Pessoa pessoa){
        if(pessoa.getNome() == null){
            throw new RuntimeException("Insira um nome e tente novamente");
        } else {
            PessoaRepository.save(pessoa);
        }
    }


    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizado) {
        Pessoa pessoaExistente = PessoaRepository.findById(id).orElse(null);
        if (pessoaExistente == null) {
            return null;
        } else {
            pessoaExistente.setNome(pessoaAtualizado.getNome());
            return PessoaRepository.save(pessoaExistente);
        }
    }




}

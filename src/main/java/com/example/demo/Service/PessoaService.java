package com.example.demo.Service;

import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
public class PessoaService {
    @Autowired
    private PessoaRepository Repository;
    public List<Pessoa> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Pessoa cadastrar(Pessoa cadastrar) {
        Assert.notNull(cadastrar.getNome(), "Error, campo nome vazio");
        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Pessoa atualizar) {
        final Pessoa marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }

    public List<Pessoa> achaNome(String nomePessoa) {
        return Repository.findByNome(nomePessoa);
    }
}
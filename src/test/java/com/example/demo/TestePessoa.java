package com.example.demo;

import com.example.demo.Controller.PessoaController;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.PessoaRepository;
import com.example.demo.Service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestePessoa {
    @MockBean
    PessoaRepository repository;

    @Autowired
    private PessoaService service;

    @Autowired
    private PessoaController controller;

    @BeforeEach
    void injectData(){
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Nicolas"));
        Mockito.when(repository.findByNome("Nicolas")).thenReturn(pessoas);
    }
    @Test
    void testControllerPessoa(){
        var pessoacontroller = controller.nameSearch("Nicolas");
        //long id = pessoacontroller.getBody().getId().LongValue();
        List<Pessoa> pessoas = pessoacontroller.getBody();
        for(int i=0;i<pessoas.size();i++){
            Assert.assertEquals("Nicolas",pessoas.get(i).getNome());
        }
    }
}
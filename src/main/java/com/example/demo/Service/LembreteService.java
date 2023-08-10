package com.example.demo.Service;

import com.example.demo.Entity.Lembretes;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repisotory.LembreteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    public List<Lembretes> findAll(){
        return lembreteRepository.findAll();
    }



    public Optional<Lembretes> procurarLembrete(Long id){
        if (!lembreteRepository.idExistente(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Lembretes> lembretes = this.lembreteRepository.findById(id);
            return lembretes;
        }
    }
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarLembrete(final Lembretes lembretes){
        if(lembretes.getLembrete() == null){
            throw new RuntimeException("Insira um nome e tente novamente");
        } else {
            lembreteRepository.save(lembretes);
        }
    }


    public Lembretes atualizarLembrete(Long id, Lembretes lembreteAtualizado) {
        Lembretes lembreteExistente = lembreteRepository.findById(id).orElse(null);
        if (lembreteExistente == null) {
            return null;
        } else {
            lembreteExistente.setLembrete(lembreteAtualizado.getLembrete());
            return lembreteRepository.save(lembreteExistente);
        }
    }







}

package com.example.demo.dto;

public class Lembretes {

    private Long id;
    private String lembrete;

    public Lembretes() {};

    public Lembretes(Long id, String lembrete) {
        this.id = id;
        this.lembrete = lembrete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return lembrete;
    }

    public void setNome(String nome) {
        this.lembrete = lembrete;
    }

}

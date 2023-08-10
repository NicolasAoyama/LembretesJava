package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lembretes")
public class Lembretes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lembrete;

    public Lembretes() {};

    public Lembretes(Long id, String nome) {
        this.id = id;
        this.lembrete = lembrete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String nome) {
        this.lembrete = lembrete;
    }

}

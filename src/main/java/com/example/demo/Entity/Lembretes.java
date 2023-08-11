package com.example.demo.Entity;

import com.example.demo.Entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "lembrete",schema = "public")
public class Lembretes extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "pessoa_id",nullable = false)
    @JsonBackReference
    private Pessoa pessoa;
    @Column(name = "lembrete",nullable = false,length = 100)
    private String lembrete;

    @Column(name = "tarefas",nullable = false,length = 100)
    private List<String> tarefas;

}
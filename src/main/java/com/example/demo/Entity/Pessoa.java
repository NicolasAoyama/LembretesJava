package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Pessoa",schema = "public")
public class Pessoa extends AbstractEntity {
    @Column(name = "Nome",nullable = false,length = 30)
    private String Nome;
    @OneToMany(mappedBy = "pessoa")
    @JsonManagedReference
    @Column(name = "tarefas",length = 100)
    private List<Lembretes> lembretes;
}
package com.example.demo.DTO;

import com.example.demo.Entity.Pessoa;

public class PessoaDTO {
    private String nome;

    public PessoaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PessoaDTO convertPessoaToDTO(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getNome());
    }

}

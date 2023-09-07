package com.example.demo.DTO;

import com.example.demo.Entity.Pessoa;

public class PessoaConverter {
    public static PessoaDTO convertPessoaToDTO(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getNome());
    }
}

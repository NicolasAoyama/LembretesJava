package com.example.demo.Repisotory;

import com.example.demo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.Nome = :NomePessoa")
    List<Pessoa> findByNome(@Param("NomePessoa") String NomePessoa);




}
package com.example.demo.Repisotory;

import com.example.demo.Entity.Lembretes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface LembreteRepository extends JpaRepository<Lembretes,Long> {
    @Query("SELECT l FROM Lembretes l " +
            "JOIN l.pessoa p " +
            "WHERE p.Nome = :NomePessoa")
    List<Lembretes> findByNome(@Param("NomePessoa") String NomePessoa);




}
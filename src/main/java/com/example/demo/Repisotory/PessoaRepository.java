package com.example.demo.Repisotory;

import com.example.demo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "select exists (select * from Pessoa where id = :id)", nativeQuery = true)
    boolean idExistente(@Param("id") final Long id);




}

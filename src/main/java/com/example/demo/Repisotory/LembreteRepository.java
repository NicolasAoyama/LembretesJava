package com.example.demo.Repisotory;

import com.example.demo.Entity.Lembretes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LembreteRepository extends JpaRepository<Lembretes, Long> {

    @Query(value = "select exists (select * from lembretes where id = :id)", nativeQuery = true)
    boolean idExistente(@Param("id") final Long id);




}

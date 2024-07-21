package com.aluracursos.literatura.Literatura.repository;

import com.aluracursos.literatura.Literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long > {
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento < :fecha AND a.fechaDeFallecimiento > :fecha")
    //List<Autor> findByFechaDeNacimientoBeforeAndFechaDeFallecimientoAfter(LocalDate fecha);
    List<Autor> encontrarAutoresVivos(@Param("fecha") LocalDate fecha);
}

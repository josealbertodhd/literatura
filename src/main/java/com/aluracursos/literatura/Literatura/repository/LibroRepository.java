package com.aluracursos.literatura.Literatura.repository;

import com.aluracursos.literatura.Literatura.model.Autor;
import com.aluracursos.literatura.Literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT DISTINCT l.autores FROM Libro l")
    List<Autor> findAllAutores();
    List<Libro> findByIdioma(String idioma);

}

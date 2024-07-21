package com.aluracursos.literatura.Literatura.dto;

import com.aluracursos.literatura.Literatura.model.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosApi(
        @JsonAlias("results") List<DatosLibro> libros) {

}

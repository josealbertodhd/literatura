package com.aluracursos.literatura.Literatura.services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}

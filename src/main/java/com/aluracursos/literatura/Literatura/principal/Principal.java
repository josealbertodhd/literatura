package com.aluracursos.literatura.Literatura.principal;

import com.aluracursos.literatura.Literatura.dto.DatosApi;
import com.aluracursos.literatura.Literatura.services.ConsumoAPI;
import com.aluracursos.literatura.Literatura.services.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void muestraElMenu(){
        int opcion = -1;
        System.out.println("Bienvenido a la app Literatura");
        while (opcion != 0){
            String menu = """
                        1 - Buscar libro por titulo
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un determinado año
                        5 - Listar libros por idioma
                        0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Digite una opcion valida!!");
                case 0:
                    System.out.println("Cerrando la aplicacion....");
                    opcion = 0;
                    break;
            }
        }
    }


    private void buscarLibroPorTitulo() {
        System.out.println("Digite libro a buscar: ");
        var buscarLibro = teclado.nextLine();
        try {
            String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + buscarLibro.replace(" ", "%20"));
            DatosApi datosApi = convierteDatos.obtenerDatos(json, DatosApi.class);
            System.out.println(datosApi.libros().get(0));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Libro no encontrado!!");
        }

    }
    private void listarLibrosRegistrados() {
    }
    private void listarAutoresRegistrados() {
    }
    private void listarAutoresVivosPorAño() {
    }
    private void listarLibrosPorIdioma() {
    }


}

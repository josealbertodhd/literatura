package com.aluracursos.literatura.Literatura.principal;

import com.aluracursos.literatura.Literatura.dto.DatosApi;
import com.aluracursos.literatura.Literatura.model.Autor;
import com.aluracursos.literatura.Literatura.model.DatosLibro;
import com.aluracursos.literatura.Literatura.model.Libro;
import com.aluracursos.literatura.Literatura.repository.AutorRepository;
import com.aluracursos.literatura.Literatura.repository.LibroRepository;
import com.aluracursos.literatura.Literatura.services.ConsumoAPI;
import com.aluracursos.literatura.Literatura.services.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.repositorio = libroRepository;
        this.autorRepository = autorRepository;
    }

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

    private DatosLibro getDatosLibro(){
        System.out.println("Digite libro a buscar: ");
        var buscarLibro = teclado.nextLine();
        DatosLibro datosLibro = null;
        try {
            String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + buscarLibro.replace(" ", "%20"));
            DatosApi datosApi = convierteDatos.obtenerDatos(json, DatosApi.class);
            datosLibro = datosApi.libros().get(0);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Libro no encontrado!!");
        }
        return datosLibro;
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datosLibro = getDatosLibro();
        if (datosLibro != null){
            Libro libro = new Libro(datosLibro);
            List<Autor> autores = datosLibro.autores().stream()
                    .map(a -> new Autor(a))
                    .collect(Collectors.toList());
            libro.setAutores(autores);

            try {
                repositorio.save(libro);
            }catch (DataIntegrityViolationException e){
                System.out.println("El libro se encuentre registrado en la base de datos!!");
            }
        }

    }

    private void listarLibrosRegistrados() {
        System.out.println(repositorio.findAll());
    }

    private void listarAutoresRegistrados() {
        System.out.println(repositorio.findAllAutores());
    }
    private void listarAutoresVivosPorAño() {
        System.out.println("Digite el año");
        int anio = teclado.nextInt();
        System.out.println(autorRepository.encontrarAutoresVivos(LocalDate.of(anio,1,1)));
    }
    private void listarLibrosPorIdioma() {
        System.out.println("""
                Digite el idioma:
    
                es - Español
                en - Ingles
                
                """);

        String idioma = teclado.nextLine();
        System.out.println(repositorio.findByIdioma(idioma));
    }


}

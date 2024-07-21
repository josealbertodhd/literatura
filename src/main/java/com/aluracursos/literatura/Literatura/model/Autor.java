package com.aluracursos.literatura.Literatura.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFallecimiento;
    @ManyToOne
    private Libro libro;

    public Autor(){
    }

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = LocalDate.of(datosAutor.fechaDeNacimiento(), 1, 1);
        this.fechaDeFallecimiento = LocalDate.of(datosAutor.fechaDeFallecimiento(), 1, 1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return  "\n------------------------\n" +
                "Nombre: " + nombre  +
                "\nFecha de Nacimiento: " + fechaDeNacimiento + '\'' +
                "\nFecha de Fallecimiento: " + fechaDeFallecimiento ;
    }
}

package com.aluracursos.literatura.Literatura;

import com.aluracursos.literatura.Literatura.principal.Principal;
import com.aluracursos.literatura.Literatura.repository.AutorRepository;
import com.aluracursos.literatura.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}
	@Autowired
	LibroRepository libroRepository;
	@Autowired
	AutorRepository autorRepository;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}
}

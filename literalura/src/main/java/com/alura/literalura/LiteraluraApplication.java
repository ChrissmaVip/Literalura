package com.alura.literalura;

import com.alura.literalura.Principal.Menu;
import com.alura.literalura.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);

	}
	@Autowired
	private LibroRepository libroRepository;

	@Override
	public void run(String... args) throws Exception {


		Menu menu = new Menu(libroRepository);
		menu.menu();



	}
}
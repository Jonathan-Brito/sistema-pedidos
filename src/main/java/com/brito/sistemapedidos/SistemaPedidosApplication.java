package com.brito.sistemapedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brito.sistemapedidos.domain.Category;
import com.brito.sistemapedidos.repositories.CategoryRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritótio");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}

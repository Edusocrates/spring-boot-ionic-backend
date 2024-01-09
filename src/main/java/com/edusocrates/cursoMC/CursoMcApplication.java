package com.edusocrates.cursoMC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {


	//gerando arquivo para o s3 manualmente, sem endpoint.
//	@Autowired
//	private S3Service s3service;


	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//s3service.uploadFile("c://teste/caminho/arquivo.txt");


	}
}

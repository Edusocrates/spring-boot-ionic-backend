package com.edusocrates.cursoMC;

import com.edusocrates.cursoMC.model.*;
import com.edusocrates.cursoMC.model.enums.EstadoPagamento;
import com.edusocrates.cursoMC.model.enums.TipoCliente;
import com.edusocrates.cursoMC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}

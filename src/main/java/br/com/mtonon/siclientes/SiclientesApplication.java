package br.com.mtonon.siclientes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.mtonon.siclientes.model.entity.Cliente;
import br.com.mtonon.siclientes.model.repository.ClienteRepository;

@SpringBootApplication
public class SiclientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiclientesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository clienteRepo) {
		return args -> {
			Cliente cliente = Cliente.builder().nome("Marcelo Tonon").cpf("72640637045").dataCadastro(LocalDate.now()).build();
			clienteRepo.save(cliente);
		};
		
	}

}

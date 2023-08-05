package br.com.mtonon.siclientes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.mtonon.siclientes.model.dto.ServicoPrestadoDTO;
import br.com.mtonon.siclientes.model.entity.Cliente;
import br.com.mtonon.siclientes.model.entity.ServicoPrestado;
import br.com.mtonon.siclientes.model.repository.ClienteRepository;
import br.com.mtonon.siclientes.model.repository.ServicoPrestadoRepository;
import br.com.mtonon.siclientes.util.BigDecimalConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		long id = dto.getIdCliente();
		Cliente cliente = clienteRepository
				.findById(id)
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente!")
						);
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor( bigDecimalConverter.converterStringParaBigDecimal(dto.getPreco()) );
		
		return repository.save(servicoPrestado);
		
	}
	
	@GetMapping
	public List<ServicoPrestado> listarServicos(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes){

		return repository.findAllByNomeClienteAndMes("%" + nome + "%", mes);
	}

}

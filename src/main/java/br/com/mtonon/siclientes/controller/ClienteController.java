package br.com.mtonon.siclientes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.siclientes.model.entity.Cliente;
import br.com.mtonon.siclientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@GetMapping("/listar")
	public List<Cliente> getAll(){
		return clienteService.getAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		cliente.setDataCadastro(LocalDate.now());
		return this.clienteService.save(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente findById(@PathVariable Long id) {
		return this.clienteService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.clienteService.delete(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		this.clienteService.update(id, cliente);
	}

}

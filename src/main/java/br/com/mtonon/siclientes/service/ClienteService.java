package br.com.mtonon.siclientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.mtonon.siclientes.model.entity.Cliente;
import br.com.mtonon.siclientes.model.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getAll(){
		return this.clienteRepository.findAll();
	}
	
	public Cliente save(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente findById(Long id) {
		return this.clienteRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public void delete(Long id) {
		this.clienteRepository
			.findById(id)
			.map(cliente -> {
				this.clienteRepository.delete(cliente);
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public void update(Long id, Cliente clienteUpdate) {
		this.clienteRepository
			.findById(id)
			.map(cliente -> {
				clienteUpdate.setId(cliente.getId());
				clienteUpdate.setDataCadastro(cliente.getDataCadastro());
				return clienteRepository.save(clienteUpdate);
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}

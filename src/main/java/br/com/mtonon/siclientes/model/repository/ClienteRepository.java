package br.com.mtonon.siclientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.siclientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

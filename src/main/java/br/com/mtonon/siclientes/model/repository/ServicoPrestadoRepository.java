package br.com.mtonon.siclientes.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mtonon.siclientes.model.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long>{

	@Query(" SELECT s FROM ServicoPrestado s join s.cliente c "
			+ "where upper(c.nome) like upper(:nome) AND MONTH(s.data) = :mes ")
	List<ServicoPrestado> findAllByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}

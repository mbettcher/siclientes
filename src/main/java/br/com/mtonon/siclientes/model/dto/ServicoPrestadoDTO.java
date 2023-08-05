package br.com.mtonon.siclientes.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
	
	@NotEmpty(message = "{campo_descricao_obrigatorio}")
	private String descricao;
	@NotEmpty(message = "{campo_preco_obrigatorio}")
	private String preco;
	@NotEmpty(message = "{campo_data_obrigatorio}")
	private String data;
	@NotNull(message = "{campo_cliente_obrigatorio}")
	private Integer idCliente;

}

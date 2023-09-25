package com.api.veiculos.apiveiculos.domain.veiculo;

import java.time.Instant;

public record VeiculoRecord(Long id, String veiculo, String marca, Integer ano, String descricao, boolean vendido, Instant created, Instant updated) {

	public VeiculoRecord(Veiculo veiculo){
		this(veiculo.getId(), veiculo.getVeiculo(), veiculo.getMarca(), veiculo.getAno(),
				veiculo.getDescricao(), veiculo.isVendido(), veiculo.getCreated(), veiculo.getUpdated());
	}
	
}

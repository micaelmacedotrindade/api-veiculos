package com.api.veiculos.apiveiculos.domain.veiculo;

import java.time.Instant;
import java.util.Calendar;

/** Classe record para geração de objeto DTO da entidade Veiculo
 * @author Micael
 * @param id Long - identificador do objeto
 * @param veiculo String - nome do veículo
 * @param marca String - fabricante do veículo
 * @param ano Integer - representa o ano em que o veículo foi fabricado
 * @param descrição String - descrição sobre o veículo
 * @param vendido boolean - informa se o veículo está ou não vendido
 * @param created Calendar - representa a data de criação do cadastro do veículo
 * @param updated Calendar - representa a data de atualização de cadastro do veículo
 * @return VeiculoRecord - Objeto do tipo VeiculoRecord
 *
 */
public record VeiculoRecord(Long id, String veiculo, String marca, Integer ano, String descricao, boolean vendido, Calendar created, Calendar updated) {

	/** Método construtor responsável por realizar o mapper de Veiculo para VeiculoRecord
	 * @author Micael
	 * @param id Long - identificador do objeto
	 * @param veiculo String - nome do veículo
	 * @param marca String - fabricante do veículo
	 * @param ano Integer - representa o ano em que o veículo foi fabricado
	 * @param descrição String - descrição sobre o veículo
	 * @param vendido boolean - informa se o veículo está ou não vendido
	 * @param created Calendar - representa a data de criação do cadastro do veículo
	 * @param updated Calendar - representa a data de atualização de cadastro do veículo
	 * @return VeiculoRecord - Objeto do tipo VeiculoRecord
	 *
	 */
	public VeiculoRecord(Veiculo veiculo){
		this(veiculo.getId(), veiculo.getVeiculo(), veiculo.getMarca(), veiculo.getAno(),
				veiculo.getDescricao(), veiculo.isVendido(), veiculo.getCreated(), veiculo.getUpdated());
	}
	
}

package com.api.veiculos.apiveiculos.domain.veiculo;

/** Classe record para geração de objeto DTO, permitindo o agrupamento consolidado
 * de quantidade de veículos por marca.
 * @author Micael
 * @param marca int - Representa a marca ou fabricante do veículo.
 * @param quantidade int - Valor que representa a quantidade de veículos.
 * @return GrupoVeiculoPorFabricanteRecord - Objeto do tipo GrupoVeiculoPorFabricanteRecord
 *
 */
public record GrupoVeiculoPorFabricanteRecord(String marca, int quantidade) {

	/**Método construtor da classe para realização de mapper de Object[] para GrupoVeiculoPorFabricanteRecord
	 * @param listaObjetos - Resultado da query que executada no banco. Traz veiculos por fabricante
	 * @return objeto do tipo GrupoVeiculoPorFabricanteRecord
	 */
	public static GrupoVeiculoPorFabricanteRecord mapGrupoVeiculoPorFabricanteRecord(Object[] listaObjetos) {
		String marca = null;
		int quantidade = 0;	
		for (int i = 0; i < listaObjetos.length; i++) {
			marca = (String) listaObjetos[0];
			quantidade = ((Long) listaObjetos[1]).intValue();
		}
		return new GrupoVeiculoPorFabricanteRecord(marca, quantidade);
	}
}

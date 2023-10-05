package com.api.veiculos.apiveiculos.domain.veiculo;


/** Classe record para geração de objeto DTO, permitindo o agrupamento consolidado
 * de quantidade de veículos por década.
 * @author Micael
 * @param decada int - Valor que representa a década de fabricação.
 * @param quantidade int - Valor que representa a quantidade de veículos.
 * @return GrupoVeiculoPorDecadaRecord - Objeto do tipo GrupoVeiculoPorDecadaRecord
 *
 */
public record GrupoVeiculoPorDecadaRecord(int decada, int quantidade) {

}

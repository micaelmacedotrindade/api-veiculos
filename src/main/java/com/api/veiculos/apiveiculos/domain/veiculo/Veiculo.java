package com.api.veiculos.apiveiculos.domain.veiculo;

import java.time.Instant;
import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe para objetos do tipo Veiculo que representa o cadastro de um veículo
 * 
 * @author Micael
 * @version 1.0
 * @since Release 01 da aplicação
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private boolean vendido;
	private Calendar created;
	private Calendar updated;

	/**
	 * Método construtor da classe para realização de mapper de VeiculoRecord para
	 * Veiculo
	 * 
	 * @param veiculoRecord - DTO do tipo VeiculoRecord
	 * @return objeto do tipo Veiculo
	 */
	public Veiculo(@Valid VeiculoRecord veiculoRecord) {
		this.id = veiculoRecord.id();
		this.veiculo = veiculoRecord.veiculo();
		this.marca = veiculoRecord.marca();
		this.ano = veiculoRecord.ano();
		this.descricao = veiculoRecord.descricao();
		this.vendido = veiculoRecord.vendido();
		this.created = veiculoRecord.created() != null ? veiculoRecord.created() : Calendar.getInstance();
		this.updated = Calendar.getInstance();
	}

	/**
	 * Método para mudar o status "vendido", atribuindo valor oposto ao atributo "vendido"
	 * @return void
	 */
	public void mudarStatusVendido() {
		this.vendido = !isVendido();
	}

}

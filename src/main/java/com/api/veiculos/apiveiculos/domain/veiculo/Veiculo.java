package com.api.veiculos.apiveiculos.domain.veiculo;

import java.time.Instant;

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
	
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private boolean vendido;
	private Instant created;
	private Instant updated;
	
	public Veiculo(@Valid VeiculoRecord veiculoRecord) {
		this.id = veiculoRecord.id();
		this.veiculo = veiculoRecord.veiculo();
		this.marca = veiculoRecord.marca();
		this.ano = veiculoRecord.ano();
		this.descricao = veiculoRecord.veiculo();
		this.vendido = veiculoRecord.vendido();
		this.created = veiculoRecord.created();
		this.updated = veiculoRecord.updated();
	}
	
}

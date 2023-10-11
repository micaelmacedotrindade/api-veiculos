package com.api.veiculos.apiveiculos.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.veiculos.apiveiculos.domain.veiculo.GrupoVeiculoPorDecadaRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.GrupoVeiculoPorFabricanteRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.Veiculo;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculoRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculosRepository;
import com.api.veiculos.apiveiculos.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

/**
 * @author micael
 *
 */
@Component
@Controller
@Path("/api/v1")
public class VeiculosResource {

	/**
	 * 
	 */
	@Autowired
	private VeiculosRepository veiculosRepository;

	/**
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("/veiculos")
	public ResponseEntity<List<VeiculoRecord>> getListaVeiculos(@QueryParam("marca") String marca,
			@QueryParam("ano") Integer ano) {
		List<VeiculoRecord> listVeiculos = Collections.emptyList();
		if ("all".equals(marca) && ano == 0) {
			listVeiculos = veiculosRepository.findAll().stream().map(VeiculoRecord::new).toList();
		} else {
			listVeiculos = veiculosRepository.findByMarcaEAno("%" + marca + "%", ano).stream().map(VeiculoRecord::new)
					.toList();
		}

		return ResponseEntity.ok(listVeiculos);
	};

	@GET
	@Produces("application/json")
	@Path("/veiculos/veiculosNaoVendidos")
	public ResponseEntity<Integer> getVeiculosNaoVendidos() {
		int qtdVeiculosNaoVendidos = veiculosRepository.findAllByVendidoFalse().stream().map(VeiculoRecord::new)
				.toList().size();
		return ResponseEntity.ok(qtdVeiculosNaoVendidos);
	};

	@GET
	@Produces("application/json")
	@Path("/veiculos/veiculosCadastradosNaSemana")
	public ResponseEntity<Integer> getVeiculosCadastradosNaSemana() {
		int qtdVeiculosNaSemana = veiculosRepository.findAllByCadastradosNaSemana();
		return ResponseEntity.ok(qtdVeiculosNaSemana);
	};

	@GET
	@Produces("application/json")
	@Path("/veiculos/veiculosPorFabricante")
	public ResponseEntity<List<GrupoVeiculoPorFabricanteRecord>> getVeiculosPorFabricante() {
		List<GrupoVeiculoPorFabricanteRecord> listaVeiculosPorFabricante = veiculosRepository
				.findAllGroupByFrabricante().stream()
				.map(objArr -> GrupoVeiculoPorFabricanteRecord.mapGrupoVeiculoPorFabricanteRecord(objArr)).toList();
		return ResponseEntity.ok(listaVeiculosPorFabricante);
	};

	@GET
	@Produces("application/json")
	@Path("/veiculos/veiculosPorDecada")
	public ResponseEntity<List<GrupoVeiculoPorDecadaRecord>> getVeiculosPorDecada() {
		List<Veiculo> listVeiculos = veiculosRepository.findAll();
		List<Integer> listDecadas = descobrirDecadas(listVeiculos);
		List<GrupoVeiculoPorDecadaRecord> veiculosPorDecada = new ArrayList<>();

		for (Integer d : listDecadas) {
			int quantidadeNaDecada = 0;
			for (Veiculo v : listVeiculos) {
				if (pertenceADecada(d, v)) {
					quantidadeNaDecada++;
				}
				;
			}
			veiculosPorDecada.add(new GrupoVeiculoPorDecadaRecord(d, quantidadeNaDecada));
		}
		return ResponseEntity.ok(veiculosPorDecada);
	};

	private boolean pertenceADecada(Integer decada, Veiculo v) {
		if (extrairDecadaDeVeiculo(v).equals(decada)) {
			return true;
		}
		return false;
	}

	private List<Integer> descobrirDecadas(List<Veiculo> listVeiculos) {
		List<Integer> listDecadas = new ArrayList<>();
		for (Veiculo v : listVeiculos) {
			listDecadas.add(extrairDecadaDeVeiculo(v));
		}
		return listDecadas.stream().distinct().collect(Collectors.toList());
	}

	private Integer extrairDecadaDeVeiculo(Veiculo v) {
		Integer decada;
		String digitos = v.getAno().toString();
		String ultimoDigito = digitos.substring(digitos.length() - 1);
		Integer ultimoDigitoInt = Integer.valueOf(ultimoDigito);

		if (ultimoDigitoInt == 0) {
			decada = v.getAno() - 10;
		} else {
			decada = v.getAno() - ultimoDigitoInt;
		}
		return decada;
	}

	@GET
	@Produces("application/json")
	@Path("/veiculos/{id}")
	public ResponseEntity<VeiculoRecord> getVeiculoById(@PathParam(value = "id") Long id)
			throws ResourceNotFoundException {
		Optional<Veiculo> veiculo = veiculosRepository.findById(id);
		if (veiculo.isPresent()) {
			return ResponseEntity.ok().body(new VeiculoRecord(veiculo.get()));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/veiculos")
	public ResponseEntity<VeiculoRecord> cadastrarVeiculo(@RequestBody VeiculoRecord veiculoRecord) {
		veiculosRepository.save(new Veiculo(veiculoRecord));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PUT
	@Consumes("application/json")
	@Path("/veiculos/{id}")
	public ResponseEntity<VeiculoRecord> atualizarVeiculo(@PathParam(value = "id") Long id,
			@Valid @RequestBody VeiculoRecord veiculoRecord) throws ResourceNotFoundException {
		Veiculo veiculo = veiculosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado :: " + id));
		Veiculo veiculoAtualizado = veiculosRepository.save(new Veiculo(veiculoRecord));
		return ResponseEntity.ok(new VeiculoRecord(veiculoAtualizado));
	}

	@PATCH
	@Consumes("application/json")
	@Path("/veiculos/{id}")
	public ResponseEntity<VeiculoRecord> atualizarApenasAnoEMarcaVeiculo(@PathParam(value = "id") Long id)
			throws ResourceNotFoundException {
		Veiculo veiculo = veiculosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado :: " + id));
		veiculo.mudarStatusVendido();
		Veiculo veiculoAtualizado = veiculosRepository.save(veiculo);
		return ResponseEntity.ok(new VeiculoRecord(veiculoAtualizado));
	}

	@DELETE
	@Path("/veiculos/{id}")
	public ResponseEntity<VeiculoRecord> deleteUser(@PathParam(value = "id") Long id) throws ResourceNotFoundException {
		Veiculo veiculo = veiculosRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado :: " + id));

		veiculosRepository.delete(veiculo);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
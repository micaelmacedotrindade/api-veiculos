package com.api.veiculos.apiveiculos.resources;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.assertj.core.condition.AnyOf;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.api.veiculos.apiveiculos.domain.veiculo.GrupoVeiculoPorDecadaRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.GrupoVeiculoPorFabricanteRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.Veiculo;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculoRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
public class VeiculosResourceTest {

	@Autowired
	WebTestClient webClient;

	@MockBean
	private VeiculosRepository veiculosRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	private Veiculo veiculo;

//    @Before
//    public void setUp(){
//    	veiculo = 
//    }

	@Test
	@DisplayName("Deveria devolver uma lista com dois veículos")
	public void getListaVeiculosComParametrosDefaultTest() throws Exception {

		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca2", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());

		List<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(v1);
		veiculos.add(v2);

		doReturn(veiculos).when(veiculosRepository).findAll();

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(veiculos));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos").queryParam("marca", "{marca}")
						.queryParam("ano", "{ano}").build("all", "0"))
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver uma lista com um veículo")
	public void getListaVeiculosComParametroMarcaPreenchido() throws Exception {

		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());

		List<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(v1);

		doReturn(veiculos).when(veiculosRepository).findByMarcaEAno(anyString(), anyInt());

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(veiculos));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos").queryParam("marca", "{marca}")
						.queryParam("ano", "{ano}").build("marca", "0"))
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver uma lista com um veículo")
	public void getListaVeiculosComParametroAnoPreenchido() throws Exception {

		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());

		List<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(v1);

		doReturn(veiculos).when(veiculosRepository).findByMarcaEAno(anyString(), anyInt());

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(veiculos));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos").queryParam("marca", "{marca}")
						.queryParam("ano", "{ano}").build("all", "2020"))
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver a quantidade de veículos com status de vendido = false")
	public void getVeiculosNaoVendidos() throws Exception {

		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca2", 2010, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());

		List<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(v1);
		veiculos.add(v2);

		doReturn(veiculos).when(veiculosRepository).findAllByVendidoFalse();

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(2));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos/veiculosNaoVendidos").build())
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver a quantidade de veículos vendidos na última semana. Igual a 1 veiculo")
	public void getVeiculosCadastradosNaSemana() throws Exception {

		doReturn(1).when(veiculosRepository).findAllByCadastradosNaSemana();

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(1));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos/veiculosCadastradosNaSemana").build())
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver uma lista de objetos contendo Frabricante e Quantidade")
	public void getVeiculosPorFabricante() throws Exception {

		Object[] obj1 = { "marca1", 50L };

		List<Object[]> grupos = new ArrayList<>();

		grupos.add(obj1);

		doReturn(grupos).when(veiculosRepository).findAllGroupByFrabricante();

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(grupos.stream()
				.map(objArr -> GrupoVeiculoPorFabricanteRecord.mapGrupoVeiculoPorFabricanteRecord(objArr)).toList()));

		this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos/veiculosPorFabricante").build())
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver uma lista de objetos contendo Década e Quantidade")
	public void getVeiculosPorDecada() throws Exception {

		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca2", 2010, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());
		Veiculo v3 = new Veiculo(2L, "teste", "marca3", 1991, "teste", false, Calendar.getInstance(),
				Calendar.getInstance());

		List<Veiculo> veiculos = new ArrayList<>();

		veiculos.add(v1);
		veiculos.add(v2);
		veiculos.add(v3);

		List<GrupoVeiculoPorDecadaRecord> veiculosPorDecada = new ArrayList<>();

		veiculosPorDecada.add(new GrupoVeiculoPorDecadaRecord(2000, 1));
		veiculosPorDecada.add(new GrupoVeiculoPorDecadaRecord(2010, 1));
		veiculosPorDecada.add(new GrupoVeiculoPorDecadaRecord(1990, 1));

		doReturn(veiculos).when(veiculosRepository).findAll();

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(veiculosPorDecada));

		this.webClient.get().uri(uriBuilder -> uriBuilder.path("boot-jersey/api/v1/veiculos/veiculosPorDecada").build())
				.exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver um veículo encontrado por meio do id recebido")
	public void getVeiculoByIdComIdEncontrado() throws Exception {

		Optional<Veiculo> veiculoOptional = Optional.ofNullable(veiculo);

		doReturn(veiculoOptional).when(veiculosRepository).findById(anyLong());

		veiculo.setId(1L);
		veiculo.setVeiculo("teste");

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.ok(veiculo));

		this.webClient.get().uri("boot-jersey/api/v1/veiculos/{id}", 1L).exchange().expectStatus().isOk().expectBody()
				.json(expectedResponseContent);
	}

	@Test
	@DisplayName("Deveria devolver o código 404")
	public void getVeiculoByIdComIdNaoEncontrado() throws Exception {

		Optional<Veiculo> veiculoOptional = Optional.ofNullable(null);

		doReturn(veiculoOptional).when(veiculosRepository).findById(anyLong());

		final String expectedResponseContent = objectMapper.writeValueAsString(ResponseEntity.notFound().build());

		this.webClient.get().uri("boot-jersey/api/v1/veiculos/{id}", 1L).exchange().expectStatus().isOk().expectBody()
				.json(expectedResponseContent);
	}

//	@Test
//	@DisplayName("Deveria devolver o código 201")
//	public void cadastrarVeiculo() throws Exception {
//
//		doReturn(veiculo).when(veiculosRepository).save(veiculo);
//		
//		VeiculoRecord veiculoRecord = new VeiculoRecord(veiculo);
//
//		veiculo.setId(1L);
//		veiculo.setVeiculo("teste");
//
//		final String expectedResponseContent = objectMapper
//				.writeValueAsString(ResponseEntity.status(HttpStatus.CREATED).build());
//
//		this.webClient.get().uri("boot-jersey/api/v1/veiculos/{id}", 1L).exchange().expectStatus().isOk().expectBody()
//				.json(expectedResponseContent);
//	}

}

package com.api.veiculos.apiveiculos.resources;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.api.veiculos.apiveiculos.domain.veiculo.Veiculo;
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
	private VeiculosResource controller;
	
	@Test
	public void getListaVeiculosParametrosDefaultTest() throws Exception {
		
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
		  .uri(uriBuilder -> uriBuilder
				    .path("boot-jersey/api/v1/veiculos")
				    .queryParam("marca", "{marca}")
				    .queryParam("ano", "{ano}")
				    .build("all", "0")).exchange().expectStatus().isOk().expectBody().json(expectedResponseContent);
	}

}

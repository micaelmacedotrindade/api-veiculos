package com.api.veiculos.apiveiculos.domain.veiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) @DataJpaTest @AutoConfigureTestDatabase(replace = Replace.NONE)
public class VeiculosRepositoryTest {

	@Autowired
	private VeiculosRepository veiculosRepository;

	@Test
	public void findAllByCadastradosNaSemanaTest() {
		Veiculo v1 = new Veiculo(1L, "teste", "teste", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "teste", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		
		veiculosRepository.save(v1);
		veiculosRepository.save(v2);
		
		int quantidadeVeiculos = veiculosRepository.findAllByCadastradosNaSemana();
		
		assertEquals(2, quantidadeVeiculos);
	}
	
	@Test
	public void findByMarcaEAnoTest() {
		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca2", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		
		veiculosRepository.save(v1);
		veiculosRepository.save(v2);
		
		List<Veiculo> veiculos = veiculosRepository.findByMarcaEAno("marca1", null);
		
		assertEquals(1, veiculos.size());
	}
	
	@Test
	public void findAllByVendidoFalse() {
		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca2", 2020, "teste", true, Calendar.getInstance(), Calendar.getInstance());
		
		veiculosRepository.save(v1);
		veiculosRepository.save(v2);
		
		List<Veiculo> veiculos = veiculosRepository.findAllByVendidoFalse();
		
		assertEquals(1, veiculos.size());
	}
	
	@Test
	public void findAllGroupByFrabricante() {
		Veiculo v1 = new Veiculo(1L, "teste", "marca1", 2020, "teste", false, Calendar.getInstance(), Calendar.getInstance());
		Veiculo v2 = new Veiculo(2L, "teste", "marca1", 2020, "teste", true, Calendar.getInstance(), Calendar.getInstance());
		
		veiculosRepository.save(v1);
		veiculosRepository.save(v2);
		
		List<Object[]> veiculos = veiculosRepository.findAllGroupByFrabricante();
		
		assertEquals(1, veiculos.size());
		assertEquals(2, ((Long)veiculos.get(0)[1]).intValue());
	}
	
}

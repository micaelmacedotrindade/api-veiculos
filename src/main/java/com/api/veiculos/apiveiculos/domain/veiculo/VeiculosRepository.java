package com.api.veiculos.apiveiculos.domain.veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Classe Repository para acesso aos dados de Veículo persistidos em banco
 * 
 * @author Micael
 * @version 1.0
 * @since Release 01 da aplicação
 */
public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {

	/**
	 * Método que busca na base de dados veículos por ano e marca
	 * 
	 * @author Micael
	 * @param ano int - Valor que representa o ano de fabricação do veiculo.
	 * @param marca String - nome da marca/fabricante.
	 * @return List<Veiculo> lista de veículos agrupados por ano e marca
	 *
	 */
	@Query(value = "SELECT * FROM veiculo v WHERE LOWER(v.marca) like LOWER(:marca) or "
			+ "v.ano = :ano", nativeQuery = true)
	List<Veiculo> findByMarcaEAno(@Param("marca") String marca, @Param("ano") Integer ano);

	/**
	 * Método que busca na base de dados veículos que não foram vendidos ainda
	 * 
	 * @return List<Veiculo> lista de veículos não vendidos
	 *
	 */
	List<Veiculo> findAllByVendidoFalse();

	/**
	 * Método que busca na base de dados veículos agrupados pro fabricante/marca
	 * 
	 * @return List<Object[]> lista de veículos agrupados por Fabricante
	 *
	 */
	@Query(value = "SELECT v.marca, count(v.id) FROM veiculo v group by v.marca", nativeQuery = true)
	List<Object[]> findAllGroupByFrabricante();

	/**
	 * Método que busca na base de dados quantidade de veículos cadastrados nos últimos 7 dias
	 * 
	 * @return int - quantidade de veículos cadastrados nos últimos 7 dias
	 *
	 */
	@Query(value = "SELECT count(v.id) FROM veiculo v \n"
			+ "where v.created between (CURRENT_DATE - INTERVAL '7 DAY')\\:\\:DATE and CURRENT_DATE", nativeQuery = true)
	int findAllByCadastradosNaSemana();

}

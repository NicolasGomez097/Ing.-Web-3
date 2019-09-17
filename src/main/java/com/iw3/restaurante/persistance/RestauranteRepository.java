package com.iw3.restaurante.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.utils.Tiempo;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Integer> {

	Optional<Restaurante> findFirstByOrderByPuntuacionDesc();
	
	Optional<List<Restaurante>> findByHoraAperturaLessThanEqual(Tiempo apertura);
	
	/*Optional<List<Comida>> findBy(Tiempo apertura, Tiempo cierre);*/
	
	@Query(value = "SELECT * FROM restaurante "
			+ "WHERE (hora_apertura <= ?1 AND hora_cierre > ?1) "
			+ "OR (hora_apertura > hora_cierre AND NOT (hora_apertura > ?1 AND hora_cierre <= ?1))"
			,nativeQuery = true)
	Optional<List<Restaurante>> obtenerRestauranteAbierto(String tiempo);
}

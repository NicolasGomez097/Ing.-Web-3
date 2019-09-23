package com.iw3.restaurante.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.restaurante.model.Restaurante;
import com.iw3.restaurante.utils.Tiempo;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Integer> {

	Optional<Restaurante> findFirstByOrderByPuntuacionDesc();	
	Optional<List<Restaurante>> findByHoraAperturaLessThanEqual(Tiempo apertura);	
	Optional<List<Restaurante>> findByNombreLike(String nombre);
	Optional<List<Restaurante>> findByComidasNombreLike(String nombre);
	Optional<List<Restaurante>> findByPuntuacion(double puntuacion);
}

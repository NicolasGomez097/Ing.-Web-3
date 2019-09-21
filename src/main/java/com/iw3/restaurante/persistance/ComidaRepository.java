package com.iw3.restaurante.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.restaurante.model.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida,Integer> {
	
	Optional<Comida> findByRestauranteNombre(String nombre);
	Optional<Comida> findFirstByRestauranteNombreOrderByPrecioAsc(String nombre);
	Optional<Comida> findFirstByRestauranteNombreOrderByPrecioDesc(String nombre);
	Optional<List<Comida>> findAllByRestauranteOrderByPrecioAsc(String nombre);
	Optional<List<Comida>> findAllByRestauranteOrderByPrecioDesc(String nombre);
	Optional<List<Comida>> findAllByRestauranteNombreOrderByNombreDesc(String nombre);

}
	
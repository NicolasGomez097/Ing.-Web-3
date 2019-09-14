package com.iw3.restaurante.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.restaurante.model.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida,Integer> {

}
	